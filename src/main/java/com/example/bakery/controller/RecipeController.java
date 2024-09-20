package com.example.bakery.controller;

import com.example.bakery.model.IngredientModel;
import com.example.bakery.model.RecipeModel;
import com.example.bakery.service.api.IngredientRestApi;
import com.example.bakery.service.api.RecipeRestApi;
import com.example.bakery.service.service.IngredientService;
import com.example.bakery.service.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String getAllRecipes(Model model) {
        List<RecipeModel> recipes = recipeService.findAll();
        List<IngredientModel> ingredients = ingredientService.findAll();
        model.addAttribute("recipes", recipes);
        model.addAttribute("ingredients", ingredients);
        model.addAttribute("recipe", new RecipeModel());
        return "RecipeHtml/recipeList";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String createRecipe(@Valid @ModelAttribute RecipeModel recipe, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<RecipeModel> recipes = recipeService.findAll();
            List<IngredientModel> ingredients = ingredientService.findAll();
            model.addAttribute("recipes", recipes);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("recipe", recipe);
            return "RecipeHtml/recipeList";
        }
        recipeService.create(recipe);
        return "redirect:/recipe";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String editRecipe(@PathVariable Long id, Model model) {
        RecipeModel recipe = recipeService.findById(id);
        List<IngredientModel> ingredients = ingredientService.findAll();
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        return "RecipeHtml/recipeEdit";
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String updateRecipe(@RequestParam("id") Long id, @Valid @ModelAttribute RecipeModel recipe, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<RecipeModel> recipes = recipeService.findAll();
            List<IngredientModel> ingredients = ingredientService.findAll();
            model.addAttribute("recipes", recipes);
            model.addAttribute("ingredients", ingredients);
            model.addAttribute("recipe", recipe);
            return "RecipeHtml/recipeList";
        }
        recipe.setId(id);
        recipeService.update( recipe);
        return "redirect:/recipe";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    public String deleteRecipe(@PathVariable Long id) {
        recipeService.delete(id);
        return "redirect:/recipe";
    }
}
