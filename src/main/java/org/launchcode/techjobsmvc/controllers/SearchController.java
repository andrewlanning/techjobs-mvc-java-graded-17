package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

// import ListController.columnChoices
import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // Create a handler to process a search request and render the updated search view.
    // Used correct annotation @PostMapping
    // displaySearchResults method takes a Model parameter
    // Takes in two other parameters, searchType and searchTerm. These were found in search.html, view search.html for locations found.
    // if isEmpty() or if equals("all") return JobData.findAll() OTHERWISE send search info to findByColumnAndValue
    // either case, store results in a jobs ArrayList.
    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam("searchType") String searchType, @RequestParam("searchTerm") String searchTerm) {
        ArrayList<Job> jobs = new ArrayList<>();

        if (searchTerm.isEmpty() || searchTerm.equals("all")) {
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        // pass jobs into the search.html view via the model param
        // pass ListController.columnChoices into the view, as the existing search handler does
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }
}

