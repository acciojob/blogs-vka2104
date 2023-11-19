package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Optional<Blog> blogOpt = blogRepository2.findById(blogId);
        if(!blogOpt.isPresent()) return new Image();
        Blog blog = blogOpt.get();

        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        Image savedImage = imageRepository2.save(image);

        blog.getImageList().add(savedImage);
        blogRepository2.save(blog);
        return savedImage;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        Optional<Image> imageOpt = imageRepository2.findById(id);
        if(!imageOpt.isPresent()) return -1;
        Image image = imageOpt.get();

        String[] screenDimensionsArr = screenDimensions.split("X");
        int screenLAndB = Integer.parseInt(screenDimensionsArr[0])*Integer.parseInt(screenDimensionsArr[1]);

        String[] imageDimensionsArr = image.getDimensions().split("X");
        int imageLAndB = Integer.parseInt(imageDimensionsArr[0])*Integer.parseInt(imageDimensionsArr[1]);
        int count = 0;
        int tempImageLAndB = imageLAndB;
        while(imageLAndB <= screenLAndB) {
            imageLAndB += tempImageLAndB;
            count++;
        }
        return count;
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
    }
}
