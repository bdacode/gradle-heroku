package org.ratpackframework.gradle

import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

class HerokuBuildpackSpec extends Specification {

    final BUILD_PACK_TASK_NAME = 'herokuBuildpack'

    Project project = ProjectBuilder.builder().build()
    HerokuBuildpack buildPack

    void setup(){
        project.apply plugin: 'heroku'
        buildPack = project.tasks.findByName(BUILD_PACK_TASK_NAME)
    }

    void "should download binary to given location"(){
        given:
        project.heroku.buildpack = new File("src/test/resources/buildpack.zip").toURI().toString()

        when:
        File binary = buildPack.downloadTo("${project.buildDir}")

        then:
        binary.exists()
    }

    void "should error on url not set"(){
        given:
        buildPack.url = null

        when:
        buildPack.downloadTo("${project.buildDir}")

        then:
        thrown(GradleException)
    }

    void cleanup(){
        project.projectDir.deleteDir()
    }

}
