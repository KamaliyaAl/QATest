# Autotest using driver

## Which test had to be done
1. Open a JetBrains IDE.
2. Use any publicly available project as a test project (I used [Quantum-Starter-Kit](https://github.com/Perfecto-Quantum/Quantum-Starter-Kit)).
3. Open **Settings**.
4. Navigate to **Version Control** → **Changelists**.
5. Select the checkbox **"Create changelists automatically"**.
6. Verify that the checkbox is selected.
7. Click **OK**.

## Repository and Dependencies
The test project is located in:
```
src/test/kotlin/com/example/ChangeListChecker.kt
```

### Issues with EAP Version
Initially, I encountered issues when the EAP (Early Access Program) version of the application was updated. Specifically, I could no longer find `LATEST-EAP-SNAPSHOT` in the Maven repositories. To resolve this, I selected the appropriate version manually:
```
251.23774.109-EAP-SNAPSHOT
```
This version ensures compatibility with my test.

### Borrowed Code from Example Repository
Since I needed **IntelliJ Community Edition (IC)** to launch a new instance of the IDE, I copied the `TestCases` class to facilitate this process.

## Running the Test
1. Clone this repository.
2. Open the project in IntelliJ IDEA.
3. Run the test located in `src/test/kotlin/com/example/ChangeListChecker`.

## Let's see how it works


https://github.com/user-attachments/assets/ce73fb2d-4b44-44e6-aa83-a6db4f6ff14a

