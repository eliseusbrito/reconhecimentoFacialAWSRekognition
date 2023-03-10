// snippet-sourcedescription:[rekognition-collection-java-search-face-matching-image-collection.java demonstrates how to search for matching faces in an Amazon Rekognition collection.]
// snippet-service:[rekognition]
// snippet-keyword:[Java]
// snippet-sourcesyntax:[java]
// snippet-keyword:[Amazon Rekognition]
// snippet-keyword:[Code Sample]
// snippet-keyword:[SearchFacesByImage]
// snippet-keyword:[Collection]
// snippet-keyword:[Image]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[2019-01-18]
// snippet-sourceauthor:[reesch(AWS)]
// snippet-start:[rekognition.java.rekognition-collection-java-search-face-matching-image-collection.complete]

/**
 * Copyright 2010-2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * This file is licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License. A copy of
 * the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.phangaws.facedetection;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.amazonaws.services.rekognition.model.FaceMatch;
import com.amazonaws.services.rekognition.model.Image;
import com.amazonaws.services.rekognition.model.S3Object;
import com.amazonaws.services.rekognition.model.SearchFacesByImageRequest;
import com.amazonaws.services.rekognition.model.SearchFacesByImageResult;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SearchFaceMatchingImageCollection {

    //Replace bucket, collectionId and photo with your values.
    //public static final String collectionId = "MyCollectionEliseuB1";
    public static final String collectionId = "ginfo-collection";
   // public static final String bucket = "MyCollectionEliseuB1";
    public static final String bucket = "ginfo-reconhecimento-facial";
    public static final String photo = "EliseuDoisMonitoresOriginal.jpg";

    public static void main(String[] args) throws Exception {

        AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();;

        //AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder
                .standard()
                .withRegion(Regions.US_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        // Get an image object from S3 bucket.
        Image image=new Image()
                .withS3Object(new S3Object()
                        .withBucket(bucket)
                        .withName(photo));


        // Search collection for faces similar to the largest face in the image.
        SearchFacesByImageRequest searchFacesByImageRequest = new SearchFacesByImageRequest()
                .withCollectionId(collectionId)
                .withImage(image)
                .withFaceMatchThreshold(70F)
                .withMaxFaces(2);

        SearchFacesByImageResult searchFacesByImageResult =
                rekognitionClient.searchFacesByImage(searchFacesByImageRequest);

        System.out.println("Faces matching largest face in image from" + photo);
        List < FaceMatch > faceImageMatches = searchFacesByImageResult.getFaceMatches();
        for (FaceMatch face: faceImageMatches) {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(face));
            System.out.println();
        }
    }

}

// snippet-end:[rekognition.java.rekognition-collection-java-search-face-matching-image-collection.complete]

// erro: Exception in thread "main" com.amazonaws.services.rekognition.model.InvalidS3ObjectException: Unable to get object metadata from S3.
// Check object key, region and/or access permissions. (Service: AmazonRekognition; Status Code: 400; Error Code: InvalidS3ObjectException; Request ID: 61697115-a4a4-4a8f-93a8-0f9e6010dfed)