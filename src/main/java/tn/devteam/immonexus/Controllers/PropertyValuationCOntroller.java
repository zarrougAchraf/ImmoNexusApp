package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.devteam.immonexus.Entities.PropertyValuation;
import tn.devteam.immonexus.Interfaces.IPropertyValuationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/PropertyValuation")
public class PropertyValuationController {
    @Autowired
    private IPropertyValuationService iPropertyValuationService;
    @PostMapping("/add")
    void addPropertyValuation(@RequestBody PropertyValuation PropertyValuation){
        iPropertyValuationService.addPropertyValuation(PropertyValuation);
    }
    @PutMapping("/update")
    void updatePropertyValuation(@RequestBody PropertyValuation PropertyValuation){
        iPropertyValuationService.updatePropertyValuation(PropertyValuation);
    }
    @DeleteMapping("/delete/{id}")
    void deletePropertyValuation(@PathVariable Long id){
        iPropertyValuationService.deletePropertyValuation(id);
    }
    @GetMapping("/display")
    List<PropertyValuation> displayPropertyValuations(){
        return iPropertyValuationService.displayPropertyValuations();
    }
    @GetMapping("/properties")
    public List<PropertyValuation> getProperties() throws Exception {
        return iPropertyValuationService.readPropertiesFromCSV();
    }
    @GetMapping("/predictprice")
    public double getPricePredection(@RequestBody PropertyValuation propertyValuation) throws Exception {
        return iPropertyValuationService.predictPrice(propertyValuation);
    }}
