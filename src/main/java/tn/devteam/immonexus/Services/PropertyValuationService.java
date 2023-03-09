package tn.devteam.immonexus.Services;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import tn.devteam.immonexus.Entities.PropertyValuation;
import tn.devteam.immonexus.Interfaces.IPropertyValuationService;
import tn.devteam.immonexus.Repository.PropertyValuationRepository;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PropertyValuationService implements IPropertyValuationService {
    @Autowired
    PropertyValuationRepository valuationRepository;
    private Model model2;
    @Override
    public void addPropertyValuation(PropertyValuation PropertyValuation) {
        valuationRepository.save(PropertyValuation);
    }

    @Override
    public void updatePropertyValuation(PropertyValuation PropertyValuation) {
        valuationRepository.save(PropertyValuation);
    }

    @Override
    public void deletePropertyValuation(Long id) {
        valuationRepository.deleteById(id);
    }

    @Override
    public List<PropertyValuation> displayPropertyValuations() {
        return valuationRepository.findAll();
    }

    public List<PropertyValuation> readPropertiesFromCSV() throws Exception {
        // Read CSV file using OpenCSV library
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\salah\\OneDrive\\Bureau\\3A\\ImmoNexusApp-main\\src\\main\\resources\\PropertyData.csv"));

        // Skip first line (header)
        reader.skip(1);

        // Initialize list of properties
        List<PropertyValuation> properties = new ArrayList<>();

        // Loop through each line of the CSV file and create a new Property object for each line
        String[] line;
        while ((line = reader.readNext()) != null) {
            PropertyValuation property = new PropertyValuation();
            property.setPrice(Double.parseDouble(line[2]));
            property.setBedrooms(Integer.parseInt(line[3]));
            property.setBathrooms(Double.parseDouble(line[4]));
            property.setSqrt_living(Double.parseDouble(line[5]));
            property.setFloor(Double.parseDouble(line[7]));
            properties.add(property);
        }

        // Close reader
        reader.close();

        return properties;
    }
    public double predictPrice(PropertyValuation userProperty) throws Exception {
        List<PropertyValuation> scrapedProperties = readPropertiesFromCSV();
        int n=scrapedProperties.size();
        double[][] features=new double[n][4] ;
        for(int i=0;i<scrapedProperties.size();i++){
            features[i][0]=scrapedProperties.get(i).getBathrooms();
            features[i][1]=scrapedProperties.get(i).getSqrt_living();
            features[i][2]=scrapedProperties.get(i).getBedrooms();
            features[i][3]=scrapedProperties.get(i).getFloor();

        }
        double[] target = new double[n];
        for(int i=0;i<scrapedProperties.size();i++){
            target[i]=scrapedProperties.get(i).getPrice();
        }

        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.newSampleData(target, features);

        // Estimate the parameters of the linear regression model
        double[] parameters = regression.estimateRegressionParameters();

        // Make a prediction for the new property
        double[] newProperty = {1.0, userProperty.getBedrooms(), userProperty.getBathrooms(), userProperty.getFloor(), userProperty.getSqrt_living()};
        double predictedPrice = 0.0;
        for (int i = 0; i < parameters.length; i++) {
            predictedPrice += parameters[i] * newProperty[i];
        }
        return predictedPrice;
    }
    public Long getPropertyValuationCountWithThreeOrMoreBedrooms(){
        return valuationRepository.getPropertyValuationCountWithThreeOrMoreBedrooms();
    }
}
