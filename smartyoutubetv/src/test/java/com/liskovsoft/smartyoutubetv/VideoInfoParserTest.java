package com.liskovsoft.smartyoutubetv;

import android.net.Uri;
import com.liskovsoft.smartyoutubetv.helpers.VideoInfoParser;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class VideoInfoParserTest {
    private InputStream mVideoInfo;
    private InputStream mHDVideoLink;

    @Before
    public void setUp() throws Exception {
        mVideoInfo = TestHelpers.openResource("get_video_info_origin");
        mHDVideoLink = TestHelpers.openResource("extract_video_link_result");
    }

    @Test
    public void testGetHDVideoLink() throws IOException {
        VideoInfoParser videoInfoParser = new VideoInfoParser(mVideoInfo);
        String result = videoInfoParser.getHDVideoLink();

        assertTrue(IOUtils.contentEquals(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)), mHDVideoLink));
    }
}
