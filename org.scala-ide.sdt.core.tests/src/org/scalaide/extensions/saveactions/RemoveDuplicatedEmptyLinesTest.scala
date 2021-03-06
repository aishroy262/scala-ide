package org.scalaide.extensions.saveactions

import org.scalaide.core.text.Document
import org.junit.Test

class RemoveDuplicatedEmptyLinesTest extends DocumentSaveActionTests {

  override def saveAction(doc: Document) = new RemoveDuplicatedEmptyLines {
    override val document = doc
  }

  @Test
  def remove_duplicated_lines() = """|^
    |class X {
    |
    |  def f = {
    |
    |    val h = 0
    |  }
    |
    |
    |
    |  def g = {
    |
    |
    |    val g = 0
    |  }
    |}
    |
    |""".stripMargin becomes """|^
    |class X {
    |
    |  def f = {
    |
    |    val h = 0
    |  }
    |
    |  def g = {
    |
    |    val g = 0
    |  }
    |}
    |""".stripMargin after SaveEvent

  @Test
  def do_not_throw_exception_on_single_empty_line() = """|^
    |class X""".stripMargin becomes """|^
    |class X""".stripMargin after SaveEvent
}
