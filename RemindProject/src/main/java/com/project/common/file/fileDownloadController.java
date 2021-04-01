package com.project.common.file;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@Controller
public class fileDownloadController {
    private static final String BOARD_IMAGE = "/Users/kim/IdeaProjects/EnglishWords_Practice/images";

    @RequestMapping(value = "/download.do")
    protected void download(@RequestParam("imageFileName") String imageFileName, @RequestParam("boardId") String boardId, HttpServletResponse response) throws Exception {
        OutputStream out = response.getOutputStream();
        String downFile = BOARD_IMAGE+"/"+boardId+"/"+imageFileName;
        File file = new File(downFile);

//        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Content-disposition", "attachment; fileName=" + imageFileName);
        FileInputStream in = new FileInputStream(file);
        byte[] Buffer = new byte[1024];
        while(true){
            int count = in.read(Buffer);
            if(count == -1){
                break;
            }
            out.write(Buffer,0,count);
        }
        in.close();
        out.close();
    }
}
