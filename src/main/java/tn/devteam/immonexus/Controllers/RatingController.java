package tn.devteam.immonexus.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.devteam.immonexus.Entities.Rating;
import tn.devteam.immonexus.Interfaces.IRatingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/Rating")
public class RatingController {
    IRatingService iRatingService;

    @PostMapping("/addRate/")
    @ResponseBody
    public void AddRating(@RequestParam("idAnnonce") Long idAnnonce,
                          @RequestParam("note") float note,
                          @RequestParam("comment")String comment){

        Rating rat=new Rating();
        rat.setNote(note);
        rat.setComment(comment);
        iRatingService.AddRating(rat, idAnnonce);

    }

    @DeleteMapping("/delete/")
    @ResponseBody
    public void deleteRate(@RequestParam("id") Long id){
        iRatingService.deleteRate(id);

    }
    @PutMapping("/update/")
    @ResponseBody
    public Rating updateRate(@RequestParam("note") float note,
                             @RequestParam("comment") String comment,
                             @RequestParam("idRate") Long idRate) {
        return iRatingService.updateRate(note,comment, idRate);

    }

    @GetMapping("/rateavg/")
    @ResponseBody
    public float rateavg(@RequestParam("annonceId") Long idA){
        return iRatingService.RateAVG(idA);

    }

    @GetMapping("/{id}")
    public Rating getRating(@PathVariable Long id) {
        Rating rating = iRatingService.getById(id);
        if (rating == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rating not found");
        }
        return rating;
    }

    @GetMapping("/annonce/")
    public List<Rating> getRatingsForAnnonce(@RequestParam("annonceId") Long annonceId) {

        return iRatingService.getByAnnonceId(annonceId);
    }
}
