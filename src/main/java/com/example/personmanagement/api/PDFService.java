package com.example.personmanagement.api;

import com.example.personmanagement.dto.PersonDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface PDFService {
    void export(HttpServletResponse response, PersonDto personDto) throws IOException;
}
