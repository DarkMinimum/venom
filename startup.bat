:: make sure that all modules ready to be executed

:: 1. start nested services

:: venomCS
start out/pub/VenomDotNet.exe

:: venomMath
start java -jar out/artifacts/venomMath_jar/venomMath.jar ua.dz.venommath.VenomMathApplication

:: venomPy
start python .\venomPy\main.py
:: 2. start port Hub
java -jar out/artifacts/portHub_jar/portHub.jar ua.dz.porthub.PortHubApplication