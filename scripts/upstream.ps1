#!/usr/bin/env pwsh

function exit_on_error {
    param (
        [string]$message
    )
    Write-Host $message
    exit 1
}

# git reset --hard HEAD

$fileContent = Get-Content -Path "gradle.properties"

$oldHash = $null
foreach ($line in $fileContent) {
    if ($line -match "^luminolCommit = (.*)$") {
        $oldHash = $matches[1].Trim()
        break
    }
}

if (-not $oldHash) {
    Write-Host "Error: Could not find 'luminolCommit' in gradle.properties."
    exit 1
}

$newHash = (Invoke-RestMethod -Uri "https://api.github.com/repos/LuminolMC/Luminol/commits/ver/1.20.6").sha

if ($oldHash -eq $newHash) {
    Write-Host "Upstream has not updated!"
    exit 0
}

Write-Host "Updating Luminol: $oldHash -> $newHash"

(Get-Content gradle.properties) -replace "luminolCommit = $oldHash", "luminolCommit = $newHash" | Set-Content gradle.properties

git add gradle.properties

./gradlew applyPatches || exit_on_error "An error occurred when merging patches!"
./gradlew rebuildPatches || exit_on_error "An error occurred when rebuilding patches!"
./gradlew createMojmapPaperclipJar || exit_on_error "An error occurred when building!"

scripts/upstreamCommit.ps1 $oldHash $newHash

Write-Host "Created new commit, please review before pushing."
