package castspell.nlp

import scala.io.Source

object Tokenizer {

  var wordsList = List[String]()

  def initialize() = {
    val source = Source.fromURL(getClass.getResource("/lowercase_english.txt"))
    wordsList = try source.getLines.toList finally source.close
    Tokenizer
  }

  def initialize(list: List[String]) = {
    wordsList = list
    Tokenizer
  }

  def tokenize(input: String): List[String] = {
    def tokenize(remainingInput: String, tokens:List[String]): List[String] = {
      if (remainingInput == "") tokens
      else {
        val potentialTokens = wordsList.filter(remainingInput.startsWith)
          if(potentialTokens.isEmpty)
            throw new NoEnoughWordsToParse(s"Unable to parse ${remainingInput}")
          else {
              val nextToken =  potentialTokens.maxBy(_.length)
              tokenize(removePrefix(remainingInput, nextToken), nextToken :: tokens)
          }
      }
    }

    tokenize(input.toLowerCase, List[String]()).reverse
  }

  private[nlp] def removePrefix(input: String, prefix: String) =
    input.substring(prefix.length)

}
