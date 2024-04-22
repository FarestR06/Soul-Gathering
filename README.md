# Soul Gathering
## Add Soul Gathering to your mod
I don't have a proper Maven server, so you'll have to use JitPack or the source jar instead. It's not ideal, I know.
### Using JitPack (Easier, doesn't always work)
#### 1. Add JitPack and Soul Gathering to `build.gradle`
This example is written in Groovy, so the syntax may vary.
Add JitPack to your Maven repositories.
```
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}
```
Next, add the mod to your dependencies.
```
dependencies {
    modImplementation 'com.github.FarestR06:Soul-Gathering:${soul_gathering_version}'
  }
```
Lastly, replace `${soul_gathering_version}` with a release tag from the [releases](https://github.com/FarestR06/Soul-Gathering/releases) section of this repo.
#### 2. Edit gradle.properties *(OPTIONAL)*
Alternatively, you can keep `${soul_gathering_version}` the same and put the release tag in `gradle.properties`.
```
# dependencies
fabric_version = a.b.c
soul_gathering_version = [tag]+[minecraft]
```
#### 3. Reload Gradle
Reload Gradle to apply your changes. Your project should now be able to use the Soul Gathering mod.
### Using Source Jar
#### 1. Download source jar
Go to this repo's [releases](https://github.com/FarestR06/Soul-Gathering/releases) and download the jar titled `soul_gathering-[tag]+[minecraft]-sources.jar`. \
*Do not pick the one without `sources` in its filename,* as it is remapped into unreadable intermediary class files.
#### 2. Add the jar to your project folder
At the root of your project, create a directory called "`libs`". \
Place the downloaded sources jar into the `libs` directory.
#### 3. Tell Gradle where the jar is located
In your `build.gradle` file, add the `libs` directory to the repositories.
```
repositories {
   flatDir {
       dirs("libs")
   }
}
```
Then implement the jar file.
```
dependencies {
   modImplementation("soul_gathering-[tag]+[minecraft]-sources.jar")
}
```
#### 4. Reload Gradle
Reload Gradle to apply your changes. Your project should now be able to use the Soul Gathering mod.
