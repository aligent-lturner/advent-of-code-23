import Util.RangeMapping;
import Util.ReadFileAsArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Day5 {

    public static void main(String[] args) {
        ArrayList<String> lines = ReadFileAsArray.execute("./input/day-5.txt");
        int seedSoilStart, soilFertilizerStart, fertilizerWaterStart, waterLightStart, lightTemperatureStart,
                temperatureHumidStart, humidLocStart;

        String seeds = lines.get(0);
        seedSoilStart = lines.indexOf("seed-to-soil map:") + 1;
        soilFertilizerStart = lines.indexOf("soil-to-fertilizer map:") + 1;
        fertilizerWaterStart = lines.indexOf("fertilizer-to-water map:") + 1;
        waterLightStart = lines.indexOf("water-to-light map:") + 1;
        lightTemperatureStart = lines.indexOf("light-to-temperature map:") + 1;
        temperatureHumidStart = lines.indexOf("temperature-to-humidity map:") + 1;
        humidLocStart = lines.indexOf("humidity-to-location map:") + 1;

        List<String> seedSoilMapLines = lines.subList(seedSoilStart, soilFertilizerStart - 2);
        List<String> soilFertilizerMapLines = lines.subList(soilFertilizerStart, fertilizerWaterStart - 2);
        List<String> fertilizerWaterMapLines = lines.subList(fertilizerWaterStart, waterLightStart - 2);
        List<String> waterLightMapLines = lines.subList(waterLightStart, lightTemperatureStart - 2);
        List<String> lightTemperatureMapLines = lines.subList(lightTemperatureStart, temperatureHumidStart - 2);
        List<String> temperatureHumidityMapLines = lines.subList(temperatureHumidStart, humidLocStart - 2);
        List<String> humidityLocationMapLines = lines.subList(humidLocStart, lines.size() - 1);

        RangeMapping seedMapping = new RangeMapping(seedSoilMapLines);
        RangeMapping soilMapping = new RangeMapping(soilFertilizerMapLines);
        seedMapping.setNext(soilMapping);
        RangeMapping fertilizerMapping = new RangeMapping(fertilizerWaterMapLines);
        soilMapping.setNext(fertilizerMapping);
        RangeMapping waterMapping = new RangeMapping(waterLightMapLines);
        fertilizerMapping.setNext(waterMapping);
        RangeMapping lightMapping = new RangeMapping(lightTemperatureMapLines);
        waterMapping.setNext(lightMapping);
        RangeMapping temperatureMapping = new RangeMapping(temperatureHumidityMapLines);
        lightMapping.setNext(temperatureMapping);
        RangeMapping humidityMapping = new RangeMapping(humidityLocationMapLines);
        temperatureMapping.setNext(humidityMapping);

        long minLocation = Long.MAX_VALUE;
        seeds = seeds.replace("seeds:", "").trim();
        String[] seedArray = seeds.split("\\s+");
        long[] seedInts = Arrays.stream(seedArray).mapToLong(Long::parseLong).toArray();
        for (long seed : seedInts) {
            long location = seedMapping.getFinalDestination(seed);
            if (location < minLocation) {
                minLocation = location;
            }
        }
        System.out.println(minLocation);
    }
}
