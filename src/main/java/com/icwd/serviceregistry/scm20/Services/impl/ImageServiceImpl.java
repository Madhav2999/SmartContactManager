package com.icwd.serviceregistry.scm20.Services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.icwd.serviceregistry.scm20.Services.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
@Service
public class ImageServiceImpl implements ImageService
{
    private Cloudinary cloudinary;

    public ImageServiceImpl(Cloudinary cloudinary)
    {
        this.cloudinary = cloudinary;
    }
    @Override
    public String uploadImage(MultipartFile contactImage, String filename)
    {
        try
        {
            byte[] data = new byte[contactImage.getInputStream().available()];
            contactImage.getInputStream().read(data);
            cloudinary.uploader().upload(data, ObjectUtils.asMap("public_id", filename));
            return this.getUrlFromPublicId(filename);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public String getUrlFromPublicId(String publicId)
    {
        return cloudinary.url().transformation(
                new Transformation().width(100).height(100).crop("fill")).generate(publicId);
    }
}
