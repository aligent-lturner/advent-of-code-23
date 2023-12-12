import Util.FindMinimumValidValue;
import Util.RangeMapping;
import Util.ReadFileAsArray;

import java.util.*;

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

        seeds = seeds.replace("seeds:", "").trim();
        String[] seedArray = seeds.split("\\s+");
        long[] seedInts = Arrays.stream(seedArray).mapToLong(Long::parseLong).toArray();
        List<String> seedSeedMapLines = new ArrayList<>();
        for (long seed: seedInts) {
            seedSeedMapLines.add(seed + " " + seed + " " + "1");
        }
        RangeMapping seedSeedMapping = new RangeMapping(seedSeedMapLines, null, "seed-seed");
        RangeMapping seedSoilMapping = new RangeMapping(seedSoilMapLines, seedSeedMapping, "seed-soil");
        RangeMapping soilFertilizerMapping = new RangeMapping(soilFertilizerMapLines, seedSoilMapping, "soil-fertilizer");
        RangeMapping fertilizerWaterMapping = new RangeMapping(fertilizerWaterMapLines, soilFertilizerMapping, "fertilizer-water");
        RangeMapping waterLightMapping = new RangeMapping(waterLightMapLines, fertilizerWaterMapping, "water-light");
        RangeMapping lightTemperatureMapping = new RangeMapping(lightTemperatureMapLines, waterLightMapping, "light-temperature");
        RangeMapping temperatureHumidityMapping = new RangeMapping(temperatureHumidityMapLines, lightTemperatureMapping, "temperature-humidity");
        RangeMapping humidityLocationMapping = new RangeMapping(humidityLocationMapLines, temperatureHumidityMapping, "humidity-location");

        // part 1
        OptionalLong minValue = FindMinimumValidValue.execute(humidityLocationMapping);
        System.out.println(minValue.isPresent() ? minValue.getAsLong() : "No Location Found");


        seedSeedMapLines = new ArrayList<>();
        for (int i = 0; i < seedInts.length-1; i = i+2) {
            seedSeedMapLines.add(seedInts[i] + " " + seedInts[i] + " " + seedInts[i+1]);
        }
        seedSeedMapping = new RangeMapping(seedSeedMapLines, null, "seed-seed");
        seedSoilMapping.setNext(seedSeedMapping);

        minValue = FindMinimumValidValue.execute(humidityLocationMapping);
        System.out.println(minValue.isPresent() ? minValue.getAsLong() : "No Location Found");
    }
}
