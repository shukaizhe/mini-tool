package com.hfut.beike.controller;

import com.hfut.beike.common.R;
import com.hfut.beike.config.MinioConfig;
import com.hfut.beike.entity.MinioBucket;
import com.hfut.beike.utils.MinioUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UploadControllerTest {

    @Mock
    private MinioUtil mockMinioUtil;
    @Mock
    private MinioConfig mockProp;

    @InjectMocks
    private UploadController uploadControllerUnderTest;

    @Test
    public void testBucketExists() {
        // Setup
        when(mockMinioUtil.bucketExists("bucketName")).thenReturn(false);

        // Run the test
        final R<?> result = uploadControllerUnderTest.bucketExists("bucketName");

        // Verify the results
    }

    @Test
    public void testMakeBucket() {
        // Setup
        when(mockMinioUtil.makeBucket("bucketName")).thenReturn(false);

        // Run the test
        final R<?> result = uploadControllerUnderTest.makeBucket("bucketName");

        // Verify the results
    }

    @Test
    public void testRemoveBucket() {
        // Setup
        when(mockMinioUtil.removeBucket("bucketName")).thenReturn(false);

        // Run the test
        final R<?> result = uploadControllerUnderTest.removeBucket("bucketName");

        // Verify the results
    }

    @Test
    public void testGetAllBuckets() {
        // Setup
        // Configure MinioUtil.getAllBuckets(...).
        final MinioBucket minioBucket = new MinioBucket();
        minioBucket.setName("name");
        minioBucket.setZonedDateTime(ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneOffset.UTC));
        final List<MinioBucket> minioBuckets = Arrays.asList(minioBucket);
        when(mockMinioUtil.getAllBuckets()).thenReturn(minioBuckets);

        // Run the test
        final R<?> result = uploadControllerUnderTest.getAllBuckets();

        // Verify the results
    }

    @Test
    public void testGetAllBuckets_MinioUtilReturnsNoItems() {
        // Setup
        when(mockMinioUtil.getAllBuckets()).thenReturn(Collections.emptyList());

        // Run the test
        final R<?> result = uploadControllerUnderTest.getAllBuckets();

        // Verify the results
    }

    @Test
    public void testUpload() {
        // Setup
        final MultipartFile file = null;

        // Run the test
        final R<?> result = uploadControllerUnderTest.upload(file);

        // Verify the results
    }

    @Test
    public void testUpload_MinioUtilReturnsNull() {
        // Setup
        final MultipartFile file = null;

        // Run the test
        final R<?> result = uploadControllerUnderTest.upload(file);

        // Verify the results
    }

    @Test
    public void testPreview() {
        // Setup
        when(mockMinioUtil.preview("fileName")).thenReturn("result");

        // Run the test
        final R<?> result = uploadControllerUnderTest.preview("fileName");

        // Verify the results
    }

    @Test
    public void testDownload() {
        // Setup
        final HttpServletResponse res = new MockHttpServletResponse();

        // Run the test
        final R<?> result = uploadControllerUnderTest.download("fileName", res);

        // Verify the results
        verify(mockMinioUtil).download(eq("fileName"), any(HttpServletResponse.class));
    }

    @Test
    public void testRemove() {
        // Setup
        when(mockProp.getBucketName()).thenReturn("result");

        // Run the test
        final R<?> result = uploadControllerUnderTest.remove("https://baidu.gitee.io/aisuda-docs/");

        // Verify the results
        verify(mockMinioUtil).remove("//baidu.gitee.io/aisuda-docs/");
    }
}
