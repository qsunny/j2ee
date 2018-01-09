package com.aaron.imagemetadata.controller;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URL;

/**
 * 读取图片元数据
 * http://localhost:8080/metadata?url=https://secure.gravatar.com/avatar/20f152f6504953476654270f77f1a0fe?s=160
 */
@Controller
@RequestMapping("/metadata")
public class MetadataController {

    @RequestMapping(method= RequestMethod.GET)
    @ResponseBody
    public Metadata getMetadata(@RequestParam(value="url") String urlAddress) throws Exception {
        URL url = new URL(urlAddress);
        return  ImageMetadataReader.readMetadata(url.openStream());
    }
}
