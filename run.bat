@echo off
REM Step 1: Create the 'class' directory if it doesn't exist
if not exist class mkdir class

REM Step 2: Compile all Java files
echo Compiling Java files...
javac -d class *.java

REM Step 3: Check for compilation success
if %errorlevel% neq 0 (
    echo Compilation failed. Please check for errors.
    exit /b
)

REM Step 4: Run the main class
echo Running the program...
cd class
java main
cd ..

REM Step 5: Done
echo Program execution completed.
