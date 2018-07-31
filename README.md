# Android SonarQube Project

### by Romell Domínguez
[![](snapshot/icono.png)](https://www.romellfudi.com/)

First, we need to configure the grade variables of Test project

The test unit cases had worked with SonarQube Framework

<style>
img[src*='#center'] { 
    width:500px;
    display: block;
    margin: auto;
}
img[src*='#center_medium'] { 
    width:300px;
    display: block;
    margin: auto;
}
</style>

[![center](snapshot/sonar.png#center_medium)](https://www.sonarqube.org/)

# Install SonarQube

Add maven repository of sonarqube and the classpath into gradle-project with:

```gradle
maven {
        url "https://plugins.gradle.org/m2/"
}
classpath "org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.3"
```

On App module, apply *jacoco* plugin to generate test report, whitch is going to upload in Sonar services:

```gradle
apply plugin: "org.sonarqube"

apply plugin: 'jacoco'
jacoco {
    toolVersion = "0.7.5.201505241946"
}
```
## Configure Android Project

Creating a task for geneate a jacoco format report with gradle.  **Worked with flavors into Android projects:**

```gradle
task jacocoTestReport(type: JacocoReport, dependsOn: ['testDebugUnitTest', 'createDebugCoverageReport'])
```

Also we need create a task for connection with the sonar, thats some properties:

* **projectKey**: name key of project reporting into sonar, that key its use for track next releases
* **url**: ruta de la ubicación del repositorio del sonarQube

```gradle
sonarqube {
    properties {
        ...
        property "sonar.projectKey", "sonar_qube_sample"
        property "sonar.projectName", "SonarQube Sample Ver. 1"
        property "sonar.projectVersion", "1.0.f"
        property "sonar.host.url", "http://localhost:9000"
        ...
    }
}
```

Now we config the tests: we enable the coverage testing, disable abort on error *'check of code with problemas critics'*, because **these errors** we will analyze it from the SonarQube, and finally we indicate that the reports should have the format jacoco.

```gradle
android {
    buildTypes {
        debug {
            testCoverageEnabled = true
        }
    }
    lintOptions {
        abortOnError false
    }
    testOptions {
        unitTests.all {
            jacoco {
                includeNoLocationClasses = true
            }
        }
    }
}
```

The last configure for quickly and efficiency:

```gradle
task exportTestRultToSonarqube(dependsOn: ['jacocoTestReport','sonarqube']){
}
```

## SonarQube Server Instance

Deploying a Sonarqube service version 7.2, run sh command worked on Ubuntu and Mac, and exe on Windows:

```sh
sh sonar.sh start
```

On Web browers, look like:

![center](snapshot/a.png#center)

![center](snapshot/b.png#center)


On dashboard we look projects, issues, rules, quality metric, and more engineering features

If all components worked well, execute the test task, testing and send it with  *exportTestResultToSonarqube* task:

```sh
./gradlew exportTestResultToSonarqube
```

When task over Cuando el task acabe tendremos la confirmación:

![center](snapshot/d.png#center)

On dashboard, we look a new project analyzed 

![center](snapshot/e.png#center)

Into the project, Look four metric: count of bug, security vulnerabilities, percentage of testing coverage, and percentage of duplicate codes

![center](snapshot/f.png#center)

We verificate the id project in url on web browser:

![center](snapshot/g.png#center)

On Sonarqube project dashboard, thats look like:

![center](snapshot/h.png#center)

Finally, if we need stop sonar service, run next sentence:

```sh
sh sonar.sh stop
```

**July, 2018**
