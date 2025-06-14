/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.*;
import java.util.Scanner;
import java.io.File;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 *
 * @author HP
 */
public class AttendanceReport {

    public static void generateWarningPDF(String name, String matric, String program, String course, String lecturer, double percent) {
        String path = "WarningLetter_" + name.replaceAll(" ", "_") + ".pdf";

        try {
            PdfWriter writer = new PdfWriter(path);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Universiti Malaysia Terengganu").setBold().setFontSize(14));
            document.add(new Paragraph("Faculty of Computer Science and Information Technology\n\n"));

            document.add(new Paragraph("Date: 24 May 2025\n"));

            document.add(new Paragraph("To: " + name + "\n"
                    + "Matric No: " + matric + "\n"
                    + "Program: " + program + "\n\n"));

            document.add(new Paragraph("Subject: Warning Letter for Low Attendance\n\n"));

            document.add(new Paragraph("Dear " + name + ",\n\n"
                    + "We are writing to formally inform you that your attendance in the course " + course
                    + " has fallen below the required threshold of 80%. As of now, your attendance record stands at "
                    + String.format("%.2f", percent) + "%.\n\n"
                    + "Please be reminded that consistent attendance is crucial for academic success and is a requirement set by the university regulations. "
                    + "Failure to improve your attendance may result in further disciplinary action or disqualification from sitting for the final examination.\n\n"
                    + "If you are facing any personal or medical issues that hinder your attendance, kindly report to the faculty office or academic advisor immediately.\n\n"));

            document.add(new Paragraph("Sincerely,\n\n"
                    + lecturer + "\n"
                    + course + " Coordinator\n"
                    + "Faculty of Computer Science and Information Technology\n"
                    + "Universiti Malaysia Terengganu"));

            document.close();
            System.out.println("PDF warning letter created: " + new File(path).getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Failed to create PDF.");
            e.printStackTrace();
        }
    }
}

