package com.scm.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.scm.helper.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService
{

    @Autowired
    private final Cloudinary cloudinary;

    public ImageServiceImpl (Cloudinary cloudinary){
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage , String fileName) {
//       String fileName = UUID.randomUUID().toString();
       try {
           byte[] data = new byte[contactImage.getInputStream().available()];
           contactImage.getInputStream().read(data);
           cloudinary.uploader().upload(data, ObjectUtils.asMap(
                   "public_id",fileName
           ));

           return this.getUrlFromPublicId(fileName);


       }catch (Exception e){
          e.printStackTrace();
          return null;
       }



    }

    @Override
    public String getUrlFromPublicId(String publicId) {

        return cloudinary.url().transformation(new Transformation<>()
                .width(AppConstants.CONTACT_IMAGE_WIDTH)
                .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                .crop(AppConstants.CONTACT_IMAGE_CROP)
        ).generate(publicId);
    }
}
