package com.github.cyberryan1.netunoapi.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeUtilsTest {

    @Test
    void getTimestampLengthFromUnformattedStringA() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "1m" ), 60L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringB() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "2m" ), 120L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringC() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "5s" ), 5L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringD() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "1h" ), 3600L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringE() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "2h" ), 7200L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringF() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "1d" ), 86400L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringG() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "2d" ), 172800L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringH() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "1w" ), 604800L );
    }

    @Test
    void getTimestampLengthFromUnformattedStringI() {
        assertEquals( TimeUtils.getTimestampLengthFromUnformattedString( "2w" ), 1209600L );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthA() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "1s" ), "1 second" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthB() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "3s" ), "3 seconds" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthC() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "1m" ), "1 minute" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthD() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "5m" ), "5 minutes" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthE() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "1h" ), "1 hour" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthF() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "7h" ), "7 hours" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthG() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "1d" ), "1 day" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthH() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "2d" ), "2 days" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthI() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "1w" ), "1 week" );
    }

    @Test
    void getFormattedLengthFromUnformattedLengthJ() {
        assertEquals( TimeUtils.getFormattedLengthFromUnformattedLength( "3w" ), "3 weeks" );
    }

    @Test
    void getTimestampLengthFromFormattedLengthA() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "1 second" ), 1L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthB() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "3 seconds" ), 3L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthC() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "1 minute" ), 60L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthD() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "19 minutes" ), 1140L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthE() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "1 hour" ), 3600L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthF() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "12 hours" ), 43200L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthG() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "1 day" ), 86400L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthH() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "6 days" ), 518400L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthI() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "1 week" ), 604800L );
    }

    @Test
    void getTimestampLengthFromFormattedLengthJ() {
        assertEquals( TimeUtils.getTimestampLengthFromFormattedLength( "3 weeks" ), 1814400L );
    }

    @Test
    void getFormattedLengthFromTimestampLengthA() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 1L ), "1 second" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthB() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 32L ), "32 seconds" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthC() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 60L ), "1 minute" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthD() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 240L ), "4 minutes" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthE() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 3600L ), "1 hour" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthF() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 25200L ), "7 hours" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthG() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 86400L ), "1 day" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthH() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 345600L ), "4 days" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthI() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 604800L ), "1 week" );
    }

    @Test
    void getFormattedLengthFromTimestampLengthJ() {
        assertEquals( TimeUtils.getFormattedLengthFromTimestampLength( 2419200L ), "4 weeks" );
    }
}