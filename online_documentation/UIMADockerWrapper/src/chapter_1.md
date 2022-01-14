# Installation

This chapter will give an introduction on how to install the library for the most common build systems. If there are still problems with the installation please report those to [github](https://github.com/texttechnologylab/UIMADockerWrapper).

## Maven
The library is available by using the jitpack repository and pulling the jar files directly from them.

Add the jitpack repository to the pom.xml file.
```
<repositories>
  <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
  </repository>
</repositories>
```

Next add the dependency for the project:

```
<dependency>
    <groupId>com.github.texttechnologylab</groupId>
    <artifactId>UIMADockerWrapper</artifactId>
    <version>-SNAPSHOT</version>
</dependency>
```

That's it, the library should now please read the instructions on the docker setup to be sure that everything is set.

## Docker
The docker daemon must accessible for the program for every example from within this documentation. This can either be achieved by running the program as root, running docker in rootless mode or by granting the current user access rights to the docker daemon. A good test is by running this command:

```bash
docker container ls
```

If the program fails with an error code like access denied, the current user does not have access to the docker daemon, and therefore the library must run either as root or will not work.

