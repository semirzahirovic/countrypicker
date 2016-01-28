package com.countrypicker;

/**
 * Created by david on 27/01/16.
 */
/**
 * A GeoPoint represents an immutable pair of latitude and longitude coordinates. Both values are
 * internally stored as integer numbers.
 */
public class GeoPoint implements Comparable<GeoPoint> {

    /**
     * Maximum possible latitude value of the map.
     */
    static final double LATITUDE_MAX = 85.05113;

    /**
     * Minimum possible latitude value of the map.
     */
    static final double LATITUDE_MIN = -85.05113;

    /**
     * Maximum possible longitude value of the map.
     */
    static final double LONGITUDE_MAX = 180;

    /**
     * Minimum possible longitude value of the map.
     */
    static final double LONGITUDE_MIN = -180;

    /**
     * Conversion factor from degrees to microdegrees.
     */
    private static final int CONVERSION_FACTOR = 1000000;

    /**
     * Stores the hash value of this object.
     */
    private final int hashCode;

    /**
     * Stores the latitude value in microdegrees.
     */
    private final int latitudeE6;

    /**
     * Stores the longitude value in microdegrees.
     */
    private final int longitudeE6;

    /**
     * Constructs a new GeoPoint with the given latitude and longitude, measured in degrees.
     *
     * @param latitude
     *            the latitude of the point. This will be limited by the minimum and maximum possible
     *            latitude value.
     * @param longitude
     *            the longitude of the point. This will be limited by the minimum and maximum possible
     *            longitude value.
     */
    public GeoPoint(double latitude, double longitude) {
        this.latitudeE6 = clipLatitude((int) (latitude * CONVERSION_FACTOR));
        this.longitudeE6 = clipLongitude((int) (longitude * CONVERSION_FACTOR));
        this.hashCode = calculateHashCode();
    }

    /**
     * Constructs a new GeoPoint with the given latitude and longitude, measured in microdegrees
     * (degrees * 10^6).
     *
     * @param latitudeE6
     *            the latitude of the point in microdegrees. This will be limited by the minimum and
     *            maximum possible latitude value.
     * @param longitudeE6
     *            the longitude of the point in microdegrees. This will be limited by the minimum and
     *            maximum possible longitude value.
     */
    public GeoPoint(int latitudeE6, int longitudeE6) {
        this.latitudeE6 = clipLatitude(latitudeE6);
        this.longitudeE6 = clipLongitude(longitudeE6);
        this.hashCode = calculateHashCode();
    }

    @Override
    public int compareTo(GeoPoint geoPoint) {
        if (this.longitudeE6 > geoPoint.longitudeE6) {
            return 1;
        } else if (this.longitudeE6 < geoPoint.longitudeE6) {
            return -1;
        } else if (this.latitudeE6 > geoPoint.latitudeE6) {
            return 1;
        } else if (this.latitudeE6 < geoPoint.latitudeE6) {
            return -1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof GeoPoint)) {
            return false;
        }
        GeoPoint other = (GeoPoint) obj;
        if (this.latitudeE6 != other.latitudeE6) {
            return false;
        } else if (this.longitudeE6 != other.longitudeE6) {
            return false;
        }
        return true;
    }

    /**
     * Returns the latitude value of this GeoPoint in degrees.
     *
     * @return the latitude value in degrees.
     */
    public double getLatitude() {
        return this.latitudeE6 / (double) CONVERSION_FACTOR;
    }

    /**
     * Returns the latitude value of this GeoPoint in microdegrees (degrees * 10^6).
     *
     * @return the latitude value in microdegrees.
     */
    public int getLatitudeE6() {
        return this.latitudeE6;
    }

    /**
     * Returns the longitude value of this GeoPoint in degrees.
     *
     * @return the longitude value in degrees.
     */
    public double getLongitude() {
        return this.longitudeE6 / (double) CONVERSION_FACTOR;
    }

    /**
     * Returns the longitude value of this GeoPoint in microdegrees (degrees * 10^6).
     *
     * @return the longitude value in microdegrees.
     */
    public int getLongitudeE6() {
        return this.longitudeE6;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public String toString() {
        return this.latitudeE6 + "," + this.longitudeE6;
    }

    /**
     * Calculates the hash value of this object.
     *
     * @return the hash value of this object.
     */
    private int calculateHashCode() {
        int result = 7;
        result = 31 * result + this.latitudeE6;
        result = 31 * result + this.longitudeE6;
        return result;
    }

    private int clipLatitude(int latitude) {
        if (latitude < LATITUDE_MIN * CONVERSION_FACTOR) {
            return (int) (LATITUDE_MIN * CONVERSION_FACTOR);
        } else if (latitude > LATITUDE_MAX * CONVERSION_FACTOR) {
            return (int) (LATITUDE_MAX * CONVERSION_FACTOR);
        } else {
            return latitude;
        }
    }

    private int clipLongitude(int longitude) {
        if (longitude < LONGITUDE_MIN * CONVERSION_FACTOR) {
            return (int) (LONGITUDE_MIN * CONVERSION_FACTOR);
        } else if (longitude > LONGITUDE_MAX * CONVERSION_FACTOR) {
            return (int) (LONGITUDE_MAX * CONVERSION_FACTOR);
        } else {
            return longitude;
        }
    }
}