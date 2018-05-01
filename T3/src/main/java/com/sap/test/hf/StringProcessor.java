package com.sap.test.hf;

import java.util.Optional;

public class StringProcessor {
  private Printer printer;
  private String currentBuffer;

  public StringProcessor(Printer printer) {
    this.printer = printer;
  }

  public Optional<String> statusAndTest() throws PrinterNotConnectedException {
    printer.printTestPage();
    return Optional.ofNullable(currentBuffer);
  }
}