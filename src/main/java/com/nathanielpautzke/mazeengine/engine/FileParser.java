package com.nathanielpautzke.mazeengine.engine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
@Slf4j
public class FileParser {

    public List<String[]> parseFile(MultipartFile file) {
        try
        {
            File convertFile = new File("src/main/resources/tmp/",file.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(convertFile);
            fos.write(file.getBytes());
            fos.close();

            FileInputStream fis= new FileInputStream("src/main/resources/tmp/" + file.getOriginalFilename());
            Scanner sc= new Scanner(fis);

            List<String[]> maze = new ArrayList<>();
            while(sc.hasNextLine())
            {
                maze.add(sc.nextLine().split(""));
            }
            sc.close();

            convertFile.delete();

            return maze;
        }
        catch(IOException e) {
            log.error("{}", e.getMessage());
        }
        return null;
    }
}


