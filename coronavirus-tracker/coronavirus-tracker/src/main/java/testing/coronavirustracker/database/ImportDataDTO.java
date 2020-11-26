package testing.coronavirustracker.database;public class ImportDataDTO {

    private int fips;
    private String admin;
    private String provinceState;
    private String countryRegion;
    private String lastUpdate;
    private double lat;
    private double longVal;
    private int confirmed;
    private int deaths;
    private int recovered;
    private int active;
    private String combinedKey;
    private double incidentRate;
    private double caseFatalityRatio;

    public int getFips() {
        return fips;
    }

    public void setFips(String fips) {
        try {
            this.fips = Integer.parseInt(fips);
        }
        catch (Exception ex){
            this.fips = -1;
        }
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(String lat) {
        try {
            this.lat = Double.parseDouble(lat);
        }
        catch (Exception ex){
            this.lat = -1;
        }
    }

    public double getLongVal() {
        return longVal;
    }

    public void setLongVal(String longVal) {
        try {
            this.longVal = Double.parseDouble(longVal);
        }
        catch (Exception ex){
            this.longVal = -1;
        }
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = Integer.parseInt(confirmed);
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = Integer.parseInt(deaths);
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = Integer.parseInt(recovered);
    }

    public int getActive() {
        return active;
    }

    public void setActive(String active) {
        try {
            this.active = Integer.parseInt(active);
        }
        catch (Exception ex){
            this.active = -1;
        }
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String stringCombinedKey) {
        this.combinedKey = stringCombinedKey;
    }

    public double getIncidentRate() {
        return incidentRate;
    }

    public void setIncidentRate(String incidentRate) {
        try {
            this.incidentRate = Double.parseDouble(incidentRate);
        }
        catch (Exception ex){
            this.incidentRate = -1;
        }
    }

    public double getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(String caseFatalityRatio) {
        try {
            this.caseFatalityRatio = Double.parseDouble(caseFatalityRatio);
        }
        catch (Exception ex){
            this.caseFatalityRatio = -1;
        }
    }
}
