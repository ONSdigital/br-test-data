package uk.gov.ons.br.csv

import org.scalatest.{FeatureSpec, GivenWhenThen}
import scalax.file.Path
import uk.gov.nationalarchives.csv.validator.AllErrorsMetaDataValidator
import uk.gov.nationalarchives.csv.validator.api.CsvValidator.SubstitutePath
import uk.gov.nationalarchives.csv.validator.api.{CsvValidator, TextFile}
import uk.gov.nationalarchives.csv.validator.schema.Schema

class CsvValidatorSpec extends FeatureSpec with GivenWhenThen {

  feature("validate test data files") {

    info("As a developer")
    info("I want to have valid test data files")
    info("So that I can use it to run services against valid synthetic data")

    val validator = new CsvValidator with AllErrorsMetaDataValidator {
      val pathSubstitutions = List[SubstitutePath]()
      val enforceCaseSensitivePathChecks = false
      val trace = false
    }

    def parse(filePath: String): Schema = validator.parseSchema(TextFile(Path.fromString(getClass.getResource(filePath).getPath))) fold(f => throw new IllegalArgumentException(f.toString()), s => s)

    scenario("Valid PAYE csv file") {

      Given(s"a valid csv file of PAYE data ")
      val payeFile = TextFile(Path.fromString(getClass.getResource("/data/PAYE.csv").getPath))

      When(s"the PAYE data csv file is validated against the schema ")
      val results = validator.validate(payeFile, parse("/schema/PAYE.csvs"), None)

      Then(s"no errors should be reported")
      assert(results.isSuccess)
    }

    scenario("Valid VAT csv file") {

      Given(s"a valid csv file of VAT data ")
      val vatFile = TextFile(Path.fromString(getClass.getResource("/data/VAT.csv").getPath))

      When(s"the VAT data csv file is validated against the schema ")
      val results = validator.validate(vatFile, parse("/schema/VAT.csvs"), None)

      Then(s"no errors should be reported")
      assert(results.isSuccess)
    }

  }


}
