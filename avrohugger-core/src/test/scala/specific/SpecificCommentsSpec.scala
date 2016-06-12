package avrohugger

import java.io.File

import avrohugger._
import avrohugger.format.SpecificRecord
import org.specs2._

class SpecificCommentsSpec extends mutable.Specification {

  "a Generator" should {

    "use the comments from idl files in their Scala counterparts." in {
      val infile = new java.io.File("avrohugger-core/src/test/avro/comments1.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.fileToFile(infile, outDir)
      val sourceRecord = scala.io.Source.fromFile(s"$outDir/com/example/NoSpaces1.scala").mkString
      sourceRecord ==== util.Util.readFile("avrohugger-core/src/test/expected/comments/specific/NoSpaces1.scala")
    }

    "use the comments from idl fields even if the record has none." in {
      val infile = new java.io.File("avrohugger-core/src/test/avro/comments2.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.fileToFile(infile, outDir)
      val sourceRecord = scala.io.Source.fromFile(s"$outDir/com/example/NoSpaces2.scala").mkString
      sourceRecord ==== util.Util.readFile("avrohugger-core/src/test/expected/comments/specific/NoSpaces2.scala")
    }

    "use the comments from idl records even if the fields have none." in {
      val infile = new java.io.File("avrohugger-core/src/test/avro/comments3.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.fileToFile(infile, outDir)
      val sourceRecord = scala.io.Source.fromFile(s"$outDir/com/example/NoSpaces3.scala").mkString
      sourceRecord ==== util.Util.readFile("avrohugger-core/src/test/expected/comments/specific/NoSpaces3.scala")
    }

    "use the comments from the whole protocol as comment to the sealed trait of an ADT." in {
      val infile = new java.io.File("avrohugger-core/src/test/avro/comments4.avdl")
      val gen = new Generator(SpecificRecord)
      val outDir = gen.defaultOutputDir + "/specific/"
      gen.fileToFile(infile, outDir)
      val sourceRecord = scala.io.Source.fromFile(s"$outDir/com/example/Example4.scala").mkString
      sourceRecord ==== util.Util.readFile("avrohugger-core/src/test/expected/comments/specific/Example4.scala")
    }

  }
}
