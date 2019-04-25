package labs.lab3.calculator.io

import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import labs.lab3.calculator.calculator.Operator
import labs.lab3.calculator.exceptions.SyntaxException
import lib.io.OutputMock.setSystemInput
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.util.*

class CommandLineHandlerTest {
    @Test
    fun `throws if unexpected token`() {
        val mock = mock<IEvaluator>()
        val scanner = Scanner(System.`in`)
        val handler = CommandLineHandler(mock, scanner)
        assertThrows<SyntaxException> { handler.eval("foo") }
    }

    @Test
    fun `var x`() {
        setSystemInput("x")
        val scanner = Scanner(System.`in`)
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.VAR.toString())
        argumentCaptor<String>().apply {
            verify(mock).setVar(capture())
            assertEquals("x", firstValue)
        }
    }

    @Test
    fun `let x = 10`() {
        setSystemInput("x = 10")
        val scanner = Scanner(System.`in`)
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.LET.toString())
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
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.LET.toString())
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
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.FN.toString())
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
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.FN.toString())
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
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.FN.toString())
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
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.FN.toString())
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
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.PRINT.toString())
        argumentCaptor<String>().apply {
            verify(mock).getValue(capture())
            assertEquals("x", firstValue)
        }
    }

    @Test
    fun printvars() {
        val scanner = Scanner(System.`in`)
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.PRINTVARS.toString())
        verify(mock, times(1)).getVars()
    }

    @Test
    fun printfns() {
        val scanner = Scanner(System.`in`)
        val mock = mock<IEvaluator>()
        val handler = CommandLineHandler(mock, scanner)
        handler.eval(ECommandToken.PRINTFNS.toString())
        verify(mock, times(1)).getFns()
    }
}
