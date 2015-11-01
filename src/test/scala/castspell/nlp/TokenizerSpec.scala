package castspell.nlp

import org.scalatest.{MustMatchers, FunSpec}

class TokenizerSpec extends FunSpec with MustMatchers {

  describe("Tokenizer") {
    it("recognize simple word") {
      Tokenizer.tokenize("the") mustBe List("the")
    }
  }

}
