// snippet-sourcedescription:[rekognition-collection-java-search-face-matching-id-collection.java demonstrates how to search for faces in a collection that match a face ID.]
// snippet-service:[rekognition]
// snippet-keyword:[Java]
// snippet-sourcesyntax:[java]
// snippet-keyword:[Amazon Rekognition]
// snippet-keyword:[Code Sample]
// snippet-keyword:[SearchFacesById]
// snippet-keyword:[Collection]
// snippet-keyword:[Image]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[2019-01-18]
// snippet-sourceauthor:[reesch(AWS)]
// snippet-start:[rekognition.java.rekognition-collection-java-search-face-matching-id-collection.complete]

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

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amazonaws.services.rekognition.model.FaceMatch;
import com.amazonaws.services.rekognition.model.SearchFacesRequest;
import com.amazonaws.services.rekognition.model.SearchFacesResult;
import java.util.List;

public class SearchFaceMatchingIdCollection {

    //Replace collectionId and faceId with the values you want to use.
    public static final String collectionId = "MyCollectionEliseuB1";
    public static final String faceId = "1d624c7a-8c57-463e-a480-3d47227f187f";

    public static void main(String[] args) throws Exception {

        AmazonRekognition rekognitionClient = AmazonRekognitionClientBuilder.defaultClient();

        ObjectMapper objectMapper = new ObjectMapper();
        // Search collection for faces matching the face id.

        SearchFacesRequest searchFacesRequest = new SearchFacesRequest()
                .withCollectionId(collectionId)
                .withFaceId(faceId)
                .withFaceMatchThreshold(70F)
                .withMaxFaces(2);

        SearchFacesResult searchFacesByIdResult =
                rekognitionClient.searchFaces(searchFacesRequest);

        System.out.println("Face matching faceId " + faceId);
        List < FaceMatch > faceImageMatches = searchFacesByIdResult.getFaceMatches();
        for (FaceMatch face: faceImageMatches) {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(face));

            System.out.println();
        }
    }

}
// snippet-end:[rekognition.java.rekognition-collection-java-search-face-matching-id-collection.complete]