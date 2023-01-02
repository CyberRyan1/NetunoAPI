---
title: Setup
layout: default
permalink: /setup/
nav_order: 2
---

# Setup
[![](https://jitpack.io/v/CyberRyan1/NetunoAPI.svg)](https://jitpack.io/#CyberRyan1/NetunoAPI)

This is the setup page for the Netuno API. You will find information on how to add it to your project (via Maven or Gradle)
and how to hook into the Netuno API instance.

{: .important }
Remember to replace LATEST_VERSION with the latest version of the Netuno API. You can find the latest version
on the [releases page](https://github.com/CyberRyan1/NetunoAPI/releases).

## Maven
If you are using Maven, then add this to your `pom.xml`

```xml
<!-- Repository -->
<repositories>
    ...
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url> <!-- If errors occur, try changing this to https://www.jitpack.io -->
    </repository>
</repositories>

<!-- Dependency -->
<dependencies>
    ...
    <dependency>
        <groupId>com.github.cyberryan1</groupId>
        <artifactId>NetunoAPI</artifactId>
        <version>LATEST_VERSION</version>
    </dependency>
</dependencies>
```

## Gradle
```groovy
// Repository
repositories {
    ...
    maven { url 'https://jitpack.io' }
}

// Dependency
dependencies {
    ...
    implementation 'com.github.cyberryan1:NetunoAPI:LATEST_VERSION'
}
```

## Hooking into the Netuno API

Here is how one might setup their main file for their plugin so that they can hook into the Netuno API instance. <br>

{: .note }
Imports will always be shown in these examples to prevent wrong file imports and other confusion.

```java
// Imports
import org.bukkit.plugin.java.JavaPlugin;
import com.github.cyberryan1.netunoapi.NetunoApi;

public class MainPlugin extends JavaPlugin {
    
    public static NetunoApi netunoApi = null;
    
    @Override
    public void onEnable() {
        
        // Grab the NetunoApi instance provided by Netuno 
        //      and save it into the netunoApi variable.
        netunoApi = this.getServer().getServicesManager().getRegistration( NetunoApi.class ).getProvider();
        // Now the Netuno API is accessible to any class 
        //      via the MainPlugin.netunoApi reference.
    }
    
}

```

---

