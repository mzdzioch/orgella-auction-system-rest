//package com.orgella.controller;
//
//import com.orgella.model.Auction;
//import com.orgella.model.Bid;
//import com.orgella.model.Person;
//import com.orgella.service.AuctionService;
//import com.orgella.service.PersonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//import java.math.BigDecimal;
//import java.util.List;
//
//@Controller
//@SessionAttributes("personLogin")
//public class AuctionController {
//
//    @Autowired
//    AuctionService auctionService;
//
//    @Autowired
//    PersonService personService;
//
//    @RequestMapping(value="/", method=RequestMethod.GET)
//    public String showPage(Model model){
//        return "index";
//    }
//
//    @RequestMapping(value="/signup", method = RequestMethod.GET)
//    public String signup(Model model){
//        Person savePerson = new Person();
//        model.addAttribute("savePerson", savePerson);
//        return "signup";
//    }
//
//    @RequestMapping(value="/signup", method = RequestMethod.POST)
//    public String signup(@Valid @ModelAttribute("savePerson") Person person, BindingResult results, Model model) {
//        if(results.hasErrors()){
//            return "signup";
//        } else if(personService.existPersonByLogin(person.getLogin())){
//            model.addAttribute("message", "User name already exists. Try another user name");
//            return "signup";
//        } else {
//            personService.savePerson(person);
//            model.addAttribute("message", "User saved!");
//            return "redirect:login.html";
//        }
//
//    }
//
//    @RequestMapping(value="/login", method=RequestMethod.GET)
//    public String showLoginPage(Model model){
//        Person personLogin = new Person();
//        model.addAttribute("personLogin", personLogin);
//        return "login";
//    }
//
//    @RequestMapping(value="/login", method = RequestMethod.POST)
//    public String login(@Valid @ModelAttribute("personLogin") Person personLogin, BindingResult result){
//
//        if(result.hasErrors()){
//            return "login";
//        } else {
//            if(personService.isLoginAndPasswordCorrect(personLogin))
//                return "redirect:success.html";
//            else
//                return "failed";
//        }
//    }
//
//    @RequestMapping(value="/addauction", method = RequestMethod.GET)
//    public String addAuction(Model model){
//
//        Auction auction = new Auction();
//        model.addAttribute("auction", auction);
//        return "addauction";
//    }
//
//    @RequestMapping(value="/addauction", method = RequestMethod.POST)
//    public String addAuction(@Valid @ModelAttribute("auction") Auction auction, BindingResult results,
//                             HttpSession session){
//        if(results.hasErrors()){
//            return "addauction";
//        } else {
//            Person person = (Person) session.getAttribute("personLogin");
//            auction.setActive(true);
//            auction.setPerson(person);
//            auctionService.saveAuction(auction);
//            return "redirect:success.html";
//        }
//    }
//
//    @RequestMapping(value="/allauctions", method = RequestMethod.GET)
//    public @ResponseBody List<Auction> getAllAuctions(){
//        return auctionService.getAllAuctions().get();
//    }
//
//    @RequestMapping(value="/success", method = RequestMethod.GET)
//    public String getActiveAuctionsForSuccess(Model model, HttpSession session){
//        List<Auction> auctions;
//        Person person = (Person) session.getAttribute("personLogin");
//
//        auctions = auctionService.getAllAuctionsWithLatestPriceAndPersonIsNot(person).get();
//
//        model.addAttribute("auctionList", auctions);
//        return "success";
//    }
//
//    @RequestMapping(value="/mainlogin", method = RequestMethod.GET)
//    public String getActiveAuctions(Model model, HttpSession session){
//        List<Auction> auctions;
//        Person person = (Person) session.getAttribute("personLogin");
//
//        auctions = auctionService.getAllAuctionsWithLatestPriceAndPersonIsNot(person).get();
//
//        model.addAttribute("auctionList", auctions);
//        return "mainlogin";
//    }
//
//
//    @RequestMapping(value="/inactiveAuctions", method = RequestMethod.GET)
//    public @ResponseBody List<Auction> getAuctions(){
//        return auctionService.getAllInactiveAuctionsWithLatestPrice().get();
//    }
//
//
//    @RequestMapping(value="/{id}/auctionview", method = RequestMethod.GET)
//    public String getAuction(@PathVariable Integer id,
//                             Model model){
//
//        Auction auction = auctionService.getAuction(id).get();
//        BigDecimal lastPrice = auctionService.getLastPrice(auction);
//        Bid bid = new Bid();
//
//        model.addAttribute("lastPrice", lastPrice);
//        model.addAttribute("auction", auction);
//        model.addAttribute("bid", bid);
//
//        return "auctionview";
//    }
//
//    @RequestMapping(value="/{id}/auctionview", method = RequestMethod.POST)
//    public String makeBid(@ModelAttribute("bid") Bid bid, @PathVariable Integer id, BindingResult results,
//                           Model model){
//
//
//        Auction auction = auctionService.getAuction(id).get();
//        model.addAttribute("auction", auction);
//
//        if(results.hasErrors()) {
//            return "/{id}/auctionview";
//        } else {
//
//            if(!auctionService.isBidHigher(auction, bid.getBidPrice())){
//                model.addAttribute("message", "Your bid is lower than current price!");
//            } else {
//                if(!auctionService.tryMakeBid(auction, bid.getBidPrice())){
//                    auctionService.setAuctionFalse(auction);
//                    model.addAttribute("message", "You have won!");
//                    model.addAttribute("lastPrice", auctionService.getLastPrice(auction));
//                } else {
//                    model.addAttribute("message", "Make a bid again");
//                    model.addAttribute("lastPrice", auctionService.getLastPrice(auction));
//                }
//            }
//        }
//
//        return "auctionview";
//
//    }
//
//}
