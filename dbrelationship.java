@Entity
@Getter
@Setter
@Table(name = "global_data")
public class GlobalData{
    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="global_id")
    private int Id;
    
    @NotNull
    @Column(name="import_date")
    private Date importDate ; 
    
    @Column(name="today_confirmed")
    private int TodayConfirmed;
    
    @Column(name="today_deaths")
    private int TodayDeaths;
    
    @Column(name="today_new_confirmed")
    private int TodayNewConfirmed;
    
    @Column(name="today_new_deaths")
    private int TodayNewDeaths;
    
    @Column(name="today_new_open_cases")
    private int TodayNewOpenCases;
    
    @Column(name="today_new_recovered")
    private int TodayNewRecovered;
    
    @Column(name="today_open_cases")
    private int TodayOpenCases;
    
    @Column(name="today_recovered")
    private int TodayRecovered;
    
    @Column(name="today_vs_yesterday_confirmed")
    private float TodayVsYesterdayConfirmed;
    
    @Column(name="today_vs_yesterday_deaths")
    private float TodayVsYesterdayDeaths;    
    
    @Column(name="today_vs_yesterday_open_cases")
    private float TodayVsYesterdayOpenCases;
    
    @Column(name="today_vs_yesterday_recovered")
    private float TodayVsYesterdayRecovered;
    
    @Column(name="yesterday_confirmed")
    private int YesterdayConfirmed;
    
    @Column(name="yesterday_deaths")
    private int YesterdayDeaths;
    
    @Column(name="yesterday_open_cases")
    private int YesterdayOpenCases;
    
    @Column(name="yesterday_recovered")
    private int YesterdayRecovered; 
}
 
 
@Entity
@Getter
@Setter
@Table(name = "country_data")
public class CountryData{
    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="country_id")
    @JoinColumn(name = "region_id", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private int CountryId;
    
    @NotNull
    @Column(name="import_date")
    private Date import_date;
    
    @NotNull
    @Column(name="country")
    private String Country;
    
    @Column(name="country_es")
    private String CountryEs;
    
    @Column(name="country_it")
    private String CountryIt;
    
    @Column(name="today_confirmed")
    private int TodayConfirmed;
    
    @Column(name="today_deaths")
    private int TodayDeaths;
    
    @Column(name="today_new_confirmed")
    private int TodayNewConfirmed;
    
    @Column(name="today_new_deaths")
    private int TodayNewDeaths;
    
    @Column(name="today_new_open_cases")
    private int TodayNewOpenCases;
    
    @Column(name="today_new_recovered")
    private int TodayNewRecovered;
    
    @Column(name="today_open_cases")
    private int TodayOpenCases;
    
    @Column(name="today_recovered")
    private int TodayRecovered;
    
    @Column(name="today_vs_yesterday_confirmed")
    private float TodayVsYesterdayConfirmed;
    
    @Column(name="today_vs_yesterday_deaths")
    private float TodayVsYesterdayDeaths;    
    
    @Column(name="today_vs_yesterday_open_cases")
    private float TodayVsYesterdayOpenCases;
    
    @Column(name="today_vs_yesterday_recovered")
    private float TodayVsYesterdayRecovered;
    
    @Column(name="yesterday_confirmed")
    private int YesterdayConfirmed;
    
    @Column(name="yesterday_deaths")
    private int YesterdayDeaths;
    
    @Column(name="yesterday_open_cases")
    private int YesterdayOpenCases;
    
    @Column(name="yesterday_recovered")
    private int YesterdayRecovered; 
}

@Entity
@Getter
@Setter
@Table(name = "regional_data")
public class RegionalData{
    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    
    @NotNull
    @Column(name="import_date")
    private Date import_date;
    
    @NotNull
    @Column(name="country_id")
    private int CountryId;
    
    @NotNull
    @Column(name="region_id")
    @JoinColumn(name = "subregion_id", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private int RegionId;
    
    @NotNull
    @Column(name="region_name")
    private String RegionName;
    
    @Column(name="today_confirmed")
    private int TodayConfirmed;
    
    @Column(name="today_deaths")
    private int TodayDeaths;
    
    @Column(name="today_new_confirmed")
    private int TodayNewConfirmed;
    
    @Column(name="today_new_deaths")
    private int TodayNewDeaths;
    
    @Column(name="today_new_open_cases")
    private int TodayNewOpenCases;
    
    @Column(name="today_new_recovered")
    private int TodayNewRecovered;
    
    @Column(name="today_open_cases")
    private int TodayOpenCases;
    
    @Column(name="today_recovered")
    private int TodayRecovered;
    
    @Column(name="today_vs_yesterday_confirmed")
    private float TodayVsYesterdayConfirmed;
    
    @Column(name="today_vs_yesterday_deaths")
    private float TodayVsYesterdayDeaths;    
    
    @Column(name="today_vs_yesterday_open_cases")
    private float TodayVsYesterdayOpenCases;
    
    @Column(name="today_vs_yesterday_recovered")
    private float TodayVsYesterdayRecovered;
    
    @Column(name="yesterday_confirmed")
    private int YesterdayConfirmed;
    
    @Column(name="yesterday_deaths")
    private int YesterdayDeaths;
    
    @Column(name="yesterday_open_cases")
    private int YesterdayOpenCases;
    
    @Column(name="yesterday_recovered")
    private int YesterdayRecovered; 
}

@Entity
@Getter
@Setter
@Table(name = "subregional_data")
public class SubregionalData{
    @Id  @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    
    @NotNull
    @Column(name="import_date")
    private Date import_date;
    
    @NotNull
    @Column(name="subregion_id")
    private int SubregionId;
    
    @NotNull
    @Column(name="region_id")
    private int RegionId;
    
    @NotNull
    @Column(name="subregion_name")
    private String SubregionName;
    
    @Column(name="today_confirmed")
    private int TodayConfirmed;
    
    @Column(name="today_deaths")
    private int TodayDeaths;
    
    @Column(name="today_new_confirmed")
    private int TodayNewConfirmed;
    
    @Column(name="today_new_deaths")
    private int TodayNewDeaths;
    
    @Column(name="today_new_open_cases")
    private int TodayNewOpenCases;
    
    @Column(name="today_new_recovered")
    private int TodayNewRecovered;
    
    @Column(name="today_open_cases")
    private int TodayOpenCases;
    
    @Column(name="today_recovered")
    private int TodayRecovered;
    
    @Column(name="today_vs_yesterday_confirmed")
    private float TodayVsYesterdayConfirmed;
    
    @Column(name="today_vs_yesterday_deaths")
    private float TodayVsYesterdayDeaths;    
    
    @Column(name="today_vs_yesterday_open_cases")
    private float TodayVsYesterdayOpenCases;
    
    @Column(name="today_vs_yesterday_recovered")
    private float TodayVsYesterdayRecovered;
    
    @Column(name="yesterday_confirmed")
    private int YesterdayConfirmed;
    
    @Column(name="yesterday_deaths")
    private int YesterdayDeaths;
    
    @Column(name="yesterday_open_cases")
    private int YesterdayOpenCases;
    
    @Column(name="yesterday_recovered")
    private int YesterdayRecovered; 
}
