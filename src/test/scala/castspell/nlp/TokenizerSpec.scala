package castspell.nlp

import org.scalatest.{MustMatchers, FunSpec}

class TokenizerSpec extends FunSpec with MustMatchers {

  val tokenizer = Tokenizer.initialize(List("quick", "dog", "cat",
    "jump", "over", "fox", "blue", "the", "brown", "lazy"))

  describe("Tokenizer removePrefix") {
    it("replace first character") {
      Tokenizer.removePrefix("the", "t") mustBe "he"
    }

    it("replace full word") {
      Tokenizer.removePrefix("cat", "cat") mustBe ""
    }
  }

  describe("Tokenizer tokenize") {
    it("recognize simple word") {
      tokenizer.tokenize("the") mustBe List("the")
    }

    it("recognize words") {
      tokenizer.tokenize("Thequickbrownfoxjumpoverlazydog") mustBe List(
        "the", "quick", "brown", "fox",
        "jump", "over", "lazy", "dog")
    }

    withClue("recognize words") {
      intercept[NoEnoughWordsToParse] {
        tokenizer.tokenize("bigos")
      }
    }
  }

}
