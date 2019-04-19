package labs.lab3.calculator.io

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import labs.lab3.calculator.IOperatable
import labs.lab3.calculator.Operator
import labs.lab3.calculator.exceptions.SyntaxException
import lib.io.OutputMock.setSystemInput
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class CommandLineHandlerTest {
    @Test
    fun `throws if unexpected token`() {
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        assertThrows<SyntaxException> { handler.getTokenHandler("foo") }
    }

    @Test
    fun `var x`() {
        setSystemInput("x")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.VAR.toString())
        method(mock)
        argumentCaptor<String>().apply {
            verify(mock).setVar(capture())
            assertEquals("x", firstValue)
        }
    }

    @Test
    fun `let x = 10`() {
        setSystemInput("x = 10")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.LET.toString())
        method(mock)
        argumentCaptor<String>().apply {
            verify(mock).setVarValue(capture(), capture())
            assertEquals("x", firstValue)
            assertEquals("10", secondValue)
        }
    }

    @Test
    fun `let x=10`() {
        setSystemInput("x=10")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.LET.toString())
        method(mock)
        argumentCaptor<String>().apply {
            verify(mock).setVarValue(capture(), capture())
            assertEquals("x", firstValue)
            assertEquals("10", secondValue)
        }
    }

    @Test
    fun `fn x = z`() {
        setSystemInput("x = z")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.FN.toString())
        method(mock)
        argumentCaptor<String>().apply {
            verify(mock).setFun(capture(), capture())
            assertEquals("x", firstValue)
            assertEquals("z", secondValue)
        }
    }

    @Test
    fun `fn x=z`() {
        setSystemInput("x=z")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.FN.toString())
        method(mock)
        argumentCaptor<String>().apply {
            verify(mock).setFun(capture(), capture())
            assertEquals("x", firstValue)
            assertEquals("z", secondValue)
        }
    }

    @Test
    fun `fn x = y + z`() {
        setSystemInput("x = y + z")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.FN.toString())
        method(mock)
        val captor = argumentCaptor<Operator>()
        argumentCaptor<String>().apply {
            verify(mock).setFun(capture(), capture(), captor.capture(), capture())
            assertEquals("x", firstValue)
            assertEquals("y", secondValue)
            assertEquals(Operator.ADD, captor.firstValue)
            assertEquals("z", thirdValue)
        }
    }

    @Test
    fun `fn x=y+z`() {
        setSystemInput("x=y+z")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.FN.toString())
        method(mock)
        val captor = argumentCaptor<Operator>()
        argumentCaptor<String>().apply {
            verify(mock).setFun(capture(), capture(), captor.capture(), capture())
            assertEquals("x", firstValue)
            assertEquals("y", secondValue)
            assertEquals(Operator.ADD, captor.firstValue)
            assertEquals("z", thirdValue)
        }
    }

    @Test
    fun `print x`() {
        setSystemInput("x")
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.PRINT.toString())
        method(mock)
        argumentCaptor<String>().apply {
            verify(mock).getValue(capture())
            assertEquals("x", firstValue)
        }
    }

    @Test
    fun printvars() {
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.PRINTVARS.toString())
        method(mock)
        verify(mock, times(1)).getVars()
    }

    @Test
    fun printfns() {
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(scanner)
        val mock = mock<IOperatable>()
        val method = handler.getTokenHandler(ECommandToken.PRINTFNS.toString())
        method(mock)
        verify(mock, times(1)).getFns()
    }
}
