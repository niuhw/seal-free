# readme
```bash
mvn archetype:generate -DgroupId=com.haima.seal -DartifactId=seal-master -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

mvn archetype:generate -DgroupId=com.haima.seal -DartifactId=seal-worker -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false



mvn archetype:generate -B \
    -DarchetypeGroupId=net.alchim31.maven -DarchetypeArtifactId=scala-archetype-simple -DarchetypeVersion=1.5 \
    -DgroupId=com.haima -DartifactId=seal-master -Dversion=0.1-SNAPSHOT -Dpackage=com.haima.master



mvn archetype:generate -B \
    -DarchetypeGroupId=net.alchim31.maven -DarchetypeArtifactId=scala-archetype-simple -DarchetypeVersion=1.5 \
    -DgroupId=com.haima -DartifactId=seal-worker -Dversion=0.1-SNAPSHOT -Dpackage=com.haima.worker




mvn scala:compile
mvn scala:run -DmainClass=



gradle  init --gradle-distribution-url "http\://localhost:8081/nexus/content/repositories/gradle-distribution/gradle-4.8.1-bin.zip" clean build


mkdir -p seal-master/src/{test,main}/{java,scala,resources}
mkdir -p seal-worker/src/{test,main}/{java,scala,resources}
```



https://github.com/yangbajing/mass-data