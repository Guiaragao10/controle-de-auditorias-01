Write-Host "========================================" -ForegroundColor Cyan
Write-Host "   Iniciando CrudApp Spring Boot" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"

Write-Host "Java Version:" -ForegroundColor Yellow
java -version
Write-Host ""

Write-Host "Iniciando aplicacao..." -ForegroundColor Green
Write-Host "Acesse: http://localhost:8080" -ForegroundColor Yellow
Write-Host "Cadastro: http://localhost:8080/cadastro" -ForegroundColor Yellow
Write-Host ""

Set-Location $PSScriptRoot
& java -jar target\crudapp-0.0.1-SNAPSHOT.jar

Write-Host ""
Write-Host "Aplicacao encerrada." -ForegroundColor Red
Read-Host "Pressione Enter para fechar"
