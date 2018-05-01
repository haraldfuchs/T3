package com.sap.test.hf;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringProcessorTest {
  @Mock
  private Printer printer;

  @Test
  public void internal_buffer_should_be_absent_after_construction() throws PrinterNotConnectedException {
    // Given
    StringProcessor processor = new StringProcessor(printer);
    // When
    Optional<String> actualBuffer = processor.statusAndTest();
    // Then
    assertFalse(actualBuffer.isPresent());
  }

  @Spy
  private SysoutPrinter sysoutPrinter;

  @Test
  public void internal_buffer_should_be_absent_after_construction_sysout() throws PrinterNotConnectedException {
    // Given
    StringProcessor processor = new StringProcessor(sysoutPrinter);
    // When
    Optional<String> actualBuffer = processor.statusAndTest();
    // Then
    assertFalse(actualBuffer.isPresent());
  }

  @Test
  public void internal_buffer_should_be_absent_after_construction_sysout_with_donothing() throws PrinterNotConnectedException {
    // Given
    StringProcessor processor = new StringProcessor(sysoutPrinter);
    doNothing().when(sysoutPrinter).printTestPage();
    // When
    Optional<String> actualBuffer = processor.statusAndTest();
    // Then
    assertFalse(actualBuffer.isPresent());
  }

  @Test(expected = PrinterNotConnectedException.class)
  public void printer_not_connected_exception_should_be_thrown_up_the_stack() throws Exception {
    // Given
    StringProcessor processor = new StringProcessor(printer);
    doThrow(new PrinterNotConnectedException()).when(printer).printTestPage();
    // When
    Optional<String> actualBuffer = processor.statusAndTest();
    // Then
    assertFalse(actualBuffer.isPresent());
  }
}