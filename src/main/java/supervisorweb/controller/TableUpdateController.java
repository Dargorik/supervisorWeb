package supervisorweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import supervisorweb.domain.*;
import supervisorweb.repos.*;
import supervisorweb.service.*;

import java.util.Map;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class TableUpdateController {
    @Autowired
    private CityService cityService;
    @Autowired
    private StreetService streetService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private PriorityListService priorityListService;
    @Autowired
    private TypeOfWorkService typeOfWorkService;
    @Autowired
    private TypeOfWorkPerformedService typeOfWorkPerformedService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private PositionDutiesService positionDutiesService;
    @Autowired
    private ListTypesInPerfomedWorkService listTypesInPerfomedWorkService;
    @Autowired
    private UserRegionsService userRegionsService;
    @Autowired
    private UserService userService;


    /**
     * methods for changing the City table
     */
    @RequestMapping("/tables/city")
    public String city(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                       Map<String, Object> model) {
        model.put("cities", cityService.findAll());
        model.put("beanUp", cityService.findById(updId));
        return "city";
    }

    @RequestMapping("/tables/add/city")
    public String addCity(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                          @RequestParam(name = "name", required = false, defaultValue = "") String name,
                          Map<String, Object> model) {
        model.put("message", cityService.add(name));
        model.put("beanUp", cityService.findById(updId));
        model.put("cities", cityService.findAll());
        return "city";
    }

    @RequestMapping("/tables/update/city")
    public String updateCity(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                             @RequestParam(name = "updName", required = false, defaultValue = "") String updName,
                             Map<String, Object> model) {
        model.put("message", cityService.update(updId, updName));
        model.put("cities", cityService.findAll());
        return "city";
    }

    @RequestMapping("/tables/delete/city")
    public String deleteCity(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                             @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                             Map<String, Object> model) {
        model.put("message", cityService.delete(delId));
        model.put("beanUp", cityService.findById(updId));
        model.put("cities", cityService.findAll());
        return "city";
    }

    /**
     * methods for changing the Street table
     */
    @RequestMapping("/tables/street")
    public String street(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                         Map<String, Object> model) {
        model.put("streets", streetService.findAll());
        model.put("beanUp", streetService.findById(updId));
        return "street";
    }

    @RequestMapping("/tables/add/street")
    public String addStreet(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                            @RequestParam(name = "name", required = false, defaultValue = "") String name,
                            Map<String, Object> model) {
        model.put("message", streetService.add(name));
        model.put("streets", streetService.findAll());
        model.put("beanUp", streetService.findById(updId));
        return "street";
    }

    @RequestMapping("/tables/update/street")
    public String updateStreet(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                               @RequestParam(name = "updName", required = false, defaultValue = "") String updName,
                               Map<String, Object> model) {
        model.put("message", streetService.update(updId, updName));
        model.put("streets", streetService.findAll());
        return "street";
    }

    @RequestMapping("/tables/delete/street")
    public String deleteStreet(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                               @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                               Map<String, Object> model) {
        model.put("message", streetService.delete(delId));
        model.put("beanUp", streetService.findById(updId));
        model.put("streets", streetService.findAll());
        return "street";
    }

    /**
     * methods for changing the Address table
     */
    @RequestMapping("/tables/address")
    public String address(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                          @RequestParam(name = "cityFilter", required = false, defaultValue = "%") String cityFilter,
                          @RequestParam(name = "streetFilter", required = false, defaultValue = "%") String streetFilter,
                          @RequestParam(name = "priorityFilter", required = false, defaultValue = "%") String priorityFilter,
                          @RequestParam(name = "regionFilter", required = false, defaultValue = "%") String regionFilter,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                          Map<String, Object> model) {
        model.put("page", addressService.findAllPage(cityFilter, streetFilter, priorityFilter, regionFilter, pageable));
        model.put("url", "/tables/address");
        model.put("cities", cityService.findAll());
        model.put("streets", streetService.findAll());
        model.put("priorities", priorityListService.findAll());
        model.put("regions", regionService.findAll());
        model.put("beanUp", addressService.findById(updId));
        model.put("cityFilter", cityFilter);
        model.put("streetFilter", streetFilter);
        model.put("priorityFilter", priorityFilter);
        model.put("regionFilter", regionFilter);
        return "address";
    }

    @RequestMapping("/tables/add/address")
    public String addAddress(@RequestParam(name = "idCity", required = false, defaultValue = "") Integer idCity,
                             @RequestParam(name = "idStreet", required = false, defaultValue = "") Integer idStreet,
                             @RequestParam(name = "houseNumber", required = false, defaultValue = "") String houseNumber,
                             @RequestParam(name = "numberFloors", required = false, defaultValue = "") Integer numberFloors,
                             @RequestParam(name = "numberEntrances", required = false, defaultValue = "") Integer numberEntrances,
                             @RequestParam(name = "idPriorityList", required = false, defaultValue = "") Integer idPriorityList,
                             @RequestParam(name = "idRegion", required = false, defaultValue = "") Integer idRegion,
                             @RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                             @RequestParam(name = "cityFilter", required = false, defaultValue = "%") String cityFilter,
                             @RequestParam(name = "streetFilter", required = false, defaultValue = "%") String streetFilter,
                             @RequestParam(name = "priorityFilter", required = false, defaultValue = "%") String priorityFilter,
                             @RequestParam(name = "regionFilter", required = false, defaultValue = "%") String regionFilter,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                             Map<String, Object> model) {
        model.put("message", addressService.add(idCity, idStreet, houseNumber, numberFloors, numberEntrances, idPriorityList, idRegion));
        model.put("page", addressService.findAllPage(cityFilter, streetFilter, priorityFilter, regionFilter, pageable));
        model.put("url", "/tables/address");
        model.put("cities", cityService.findAll());
        model.put("streets", streetService.findAll());
        model.put("priorities", priorityListService.findAll());
        model.put("regions", regionService.findAll());
        model.put("beanUp", addressService.findById(updId));
        model.put("cityFilter", cityFilter);
        model.put("streetFilter", streetFilter);
        model.put("priorityFilter", priorityFilter);
        model.put("regionFilter", regionFilter);
        return "address";
    }

    @RequestMapping("/tables/update/address")
    public String updateAddress(@RequestParam(name = "updCityId", required = false, defaultValue = "0") Integer updCityId,
                                @RequestParam(name = "updStreetId", required = false, defaultValue = "0") Integer updStreetId,
                                @RequestParam(name = "updHouseNumber", required = false, defaultValue = "") String updHouseNumber,
                                @RequestParam(name = "updNumberFloors", required = false, defaultValue = "0") Integer updNumberFloors,
                                @RequestParam(name = "updNumberEntrances", required = false, defaultValue = "0") Integer updNumberEntrances,
                                @RequestParam(name = "updPriorityListId", required = false, defaultValue = "0") Integer updPriorityListId,
                                @RequestParam(name = "updRegionId", required = false, defaultValue = "0") Integer updRegionId,
                                @RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                @RequestParam(name = "cityFilter", required = false, defaultValue = "%") String cityFilter,
                                @RequestParam(name = "streetFilter", required = false, defaultValue = "%") String streetFilter,
                                @RequestParam(name = "priorityFilter", required = false, defaultValue = "%") String priorityFilter,
                                @RequestParam(name = "regionFilter", required = false, defaultValue = "%") String regionFilter,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                Map<String, Object> model) {
        model.put("message", addressService.update(updId, updCityId, updStreetId, updHouseNumber, updNumberFloors, updNumberEntrances, updPriorityListId, updRegionId));
        model.put("page", addressService.findAllPage(cityFilter, streetFilter, priorityFilter, regionFilter, pageable));
        model.put("url", "/tables/address");
        model.put("cities", cityService.findAll());
        model.put("streets", streetService.findAll());
        model.put("priorities", priorityListService.findAll());
        model.put("regions", regionService.findAll());
        model.put("cityFilter", cityFilter);
        model.put("streetFilter", streetFilter);
        model.put("priorityFilter", priorityFilter);
        model.put("regionFilter", regionFilter);
        return "address";
    }

    @RequestMapping("/tables/delete/address")
    public String deleteAddress(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                @RequestParam(name = "cityFilter", required = false, defaultValue = "%") String cityFilter,
                                @RequestParam(name = "streetFilter", required = false, defaultValue = "%") String streetFilter,
                                @RequestParam(name = "priorityFilter", required = false, defaultValue = "%") String priorityFilter,
                                @RequestParam(name = "regionFilter", required = false, defaultValue = "%") String regionFilter,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                                Map<String, Object> model) {
        model.put("message", addressService.delete(delId));
        model.put("page", addressService.findAllPage(cityFilter, streetFilter, priorityFilter, regionFilter, pageable));
        model.put("url", "/tables/address");
        model.put("cities", cityService.findAll());
        model.put("streets", streetService.findAll());
        model.put("priorities", priorityListService.findAll());
        model.put("regions", regionService.findAll());
        model.put("beanUp", addressService.findById(updId));
        model.put("cityFilter", cityFilter);
        model.put("streetFilter", streetFilter);
        model.put("priorityFilter", priorityFilter);
        model.put("regionFilter", regionFilter);
        return "address";
    }

    /**
     * methods for changing the Region table
     */
    @RequestMapping("/tables/region")
    public String region(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                         Map<String, Object> model) {
        model.put("regions", regionService.findAll());
        model.put("beanUp", regionService.findById(updId));
        return "region";
    }

    @RequestMapping("/tables/add/region")
    public String addRegion(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                            @RequestParam(name = "name", required = false, defaultValue = "") String name,
                            Map<String, Object> model) {
        model.put("message", regionService.add(name));
        model.put("regions", regionService.findAll());
        model.put("beanUp", regionService.findById(updId));
        return "region";
    }

    @RequestMapping("/tables/update/region")
    public String updateRegion(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                               @RequestParam(name = "updName", required = false, defaultValue = "") String updName,
                               Map<String, Object> model) {
        model.put("message", regionService.update(updId, updName));
        model.put("regions", regionService.findAll());
        return "region";
    }

    @RequestMapping("/tables/delete/region")
    public String deleteRegion(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                               @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                               Map<String, Object> model) {
        model.put("message", regionService.delete(delId));
        model.put("regions", regionService.findAll());
        model.put("beanUp", regionService.findById(updId));
        return "region";
    }

    /**
     * methods for changing the PriorityList table
     */
    @RequestMapping("/tables/priorityList")
    public String priorityList(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                               Map<String, Object> model) {
        model.put("prioritiesList", priorityListService.findAll());
        model.put("beanUp", priorityListService.findById(updId));
        return "priorityList";
    }

    @RequestMapping("/tables/add/priorityList")
    public String addPriorityList(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                  @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                  @RequestParam(name = "number", required = false, defaultValue = "") Integer number,
                                  Map<String, Object> model) {
        model.put("message", priorityListService.add(name, number));
        model.put("prioritiesList", priorityListService.findAll());
        model.put("beanUp", priorityListService.findById(updId));
        return "priorityList";
    }

    @RequestMapping("/tables/update/priorityList")
    public String updatePriorityList(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                     @RequestParam(name = "updName", required = false, defaultValue = "") String updName,
                                     @RequestParam(name = "updNumber", required = false, defaultValue = "") Integer number,
                                     Map<String, Object> model) {
        model.put("message", priorityListService.update(updId, updName, number));
        model.put("prioritiesList", priorityListService.findAll());
        return "priorityList";
    }

    @RequestMapping("/tables/delete/priorityList")
    public String deletePriorityList(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                     @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                     Map<String, Object> model) {
        model.put("message", priorityListService.delete(delId));
        model.put("prioritiesList", priorityListService.findAll());
        model.put("beanUp", priorityListService.findById(updId));
        return "priorityList";
    }

    /**
     * methods for changing the TypeOfWork table
     */
    @RequestMapping("/tables/typeOfWork")
    public String typeOfWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                             Map<String, Object> model) {
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", typeOfWorkService.findById(updId));
        return "typeOfWork";
    }

    @RequestMapping("/tables/add/typeOfWork")
    public String addTypeOfWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                Map<String, Object> model) {
        model.put("message", typeOfWorkService.add(name));
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", typeOfWorkService.findById(updId));
        return "typeOfWork";
    }

    @RequestMapping("/tables/update/typeOfWork")
    public String updateTypeOfWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                   @RequestParam(name = "updName", required = false, defaultValue = "") String updName,
                                   Map<String, Object> model) {
        model.put("message", typeOfWorkService.update(updId, updName));
        model.put("typesOfWork", typeOfWorkService.findAll());
        return "typeOfWork";
    }

    @RequestMapping("/tables/delete/typeOfWork")
    public String deleteTypeOfWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                   @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                   Map<String, Object> model) {
        model.put("message", typeOfWorkService.delete(delId));
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", typeOfWorkService.findById(updId));
        return "typeOfWork";
    }

    /**
     * methods for changing the TypeOfWorkPerformed table
     */
    @RequestMapping("/tables/typeOfWorkPerformed")
    public String typeOfWorkPerformed(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                      Map<String, Object> model) {
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("beanUp", typeOfWorkPerformedService.findById(updId));
        return "typeOfWorkPerformed";
    }

    @RequestMapping("/tables/add/typeOfWorkPerformed")
    public String addTypeOfWorkPerformed(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                         @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                         Map<String, Object> model) {
        model.put("message", typeOfWorkPerformedService.add(name));
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("beanUp", typeOfWorkPerformedService.findById(updId));
        return "typeOfWorkPerformed";
    }

    @RequestMapping("/tables/update/typeOfWorkPerformed")
    public String updateTypeOfWorkPerformed(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                            @RequestParam(name = "updName", required = false, defaultValue = "") String updName,
                                            Map<String, Object> model) {
        model.put("message", typeOfWorkPerformedService.update(updId, updName));
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        return "typeOfWorkPerformed";
    }

    @RequestMapping("/tables/delete/typeOfWorkPerformed")
    public String deleteTypeOfWorkPerformed(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                            @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                            Map<String, Object> model) {
        model.put("message", typeOfWorkPerformedService.delete(delId));
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("beanUp", typeOfWorkPerformedService.findById(updId));
        return "typeOfWorkPerformed";
    }

    /**
     * methods for changing the Position table
     */
    @RequestMapping("/tables/position")
    public String position(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                           Map<String, Object> model) {
        model.put("beanUp", positionService.findById(updId));
        model.put("positions", positionService.findAll());
        return "position";
    }

    @RequestMapping("/tables/add/position")
    public String addPosition(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                              @RequestParam(name = "name", required = false, defaultValue = "") String name,
                              Map<String, Object> model) {
        model.put("message", positionService.add(name));
        model.put("positions", positionService.findAll());
        model.put("beanUp", positionService.findById(updId));
        return "position";
    }

    @RequestMapping("/tables/update/position")
    public String updatePosition(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                 @RequestParam(name = "updName", required = false, defaultValue = "0") String updName,
                                 Map<String, Object> model) {
        model.put("message", positionService.update(updId, updName));
        model.put("positions", positionService.findAll());
        return "position";
    }

    @RequestMapping("/tables/delete/position")
    public String deletePosition(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                 @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                 Map<String, Object> model) {
        model.put("message", positionService.delete(delId));
        model.put("beanUp", positionService.findById(updId));
        model.put("positions", positionService.findAll());
        return "position";
    }

    /**
     * methods for changing the PositionDuties table
     */
    @RequestMapping("/tables/positionDuties")
    public String positionDuties(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                 @RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                                 @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                 Map<String, Object> model) {
        model.put("positionsDuties", positionDutiesService.findAll());
        model.put("positions", positionService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", positionDutiesService.findById(updId));
        Position position = positionService.findById(positionFilter);
        model.put("positionFilter", position == null ? 0 : position.getIdPosition());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "positionDuties";
    }

    @RequestMapping("/tables/add/positionDuties")
    public String addPositionDuties(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                    @RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                                    @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                    @RequestParam(name = "idPosition", required = false, defaultValue = "0") Integer idPosition,
                                    @RequestParam(name = "idTypeOfWork", required = false, defaultValue = "0") Integer idTypeOfWork,
                                    Map<String, Object> model) {
        model.put("message", positionDutiesService.add(idPosition, idTypeOfWork));
        model.put("positionsDuties", positionDutiesService.findAll());
        model.put("positions", positionService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", positionDutiesService.findById(updId));
        Position position = positionService.findById(positionFilter);
        model.put("positionFilter", position == null ? 0 : position.getIdPosition());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "positionDuties";
    }

    @RequestMapping("/tables/update/positionDuties")
    public String updatePositionDuties(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                       @RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                                       @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                       @RequestParam(name = "updIdPosition", required = false, defaultValue = "0") Integer updIdPosition,
                                       @RequestParam(name = "updIdTypeOfWork", required = false, defaultValue = "0") Integer updIdTypeOfWork,
                                       Map<String, Object> model) {
        model.put("message", positionDutiesService.update(updId, updIdPosition, updIdTypeOfWork));
        model.put("positionsDuties", positionDutiesService.findAll());
        model.put("positions", positionService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        Position position = positionService.findById(positionFilter);
        model.put("positionFilter", position == null ? 0 : position.getIdPosition());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "positionDuties";
    }

    @RequestMapping("/tables/delete/positionDuties")
    public String deletePositionDuties(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                       @RequestParam(name = "positionFilter", required = false, defaultValue = "0") Integer positionFilter,
                                       @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                       @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                       Map<String, Object> model) {
        model.put("message", positionDutiesService.delete(delId));
        model.put("positionsDuties", positionDutiesService.findAll());
        model.put("positions", positionService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", positionDutiesService.findById(updId));
        Position position = positionService.findById(positionFilter);
        model.put("positionFilter", position == null ? 0 : position.getIdPosition());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "positionDuties";
    }

    /**
     * methods for changing the ListTypesInPerfomedWork table
     */
    @RequestMapping("/tables/listTypesInPerfomedWork")
    public String listTypesInPerfomedWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                          @RequestParam(name = "typeOfWorkPerformedFilter", required = false, defaultValue = "0") Integer typeOfWorkPerformedFilter,
                                          @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                          Map<String, Object> model) {
        model.put("typesInPerfomedWork", listTypesInPerfomedWorkService.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", listTypesInPerfomedWorkService.findById(updId));
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedService.findById(typeOfWorkPerformedFilter);
        model.put("typeOfWorkPerformedFilter", typeOfWorkPerformed == null ? 0 : typeOfWorkPerformed.getIdTypeOfWorkPerformed());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "listTypesInPerfomedWork";
    }

    @RequestMapping("/tables/add/listTypesInPerfomedWork")
    public String addListTypesInPerfomedWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                             @RequestParam(name = "typeOfWorkPerformedFilter", required = false, defaultValue = "0") Integer typeOfWorkPerformedFilter,
                                             @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                             @RequestParam(name = "idTypeOfWorkPerformed", required = false, defaultValue = "") Integer idTypeOfWorkPerformed,
                                             @RequestParam(name = "idTypeOfWork", required = false, defaultValue = "") Integer idTypeOfWork,
                                             Map<String, Object> model) {
        model.put("message", listTypesInPerfomedWorkService.add(idTypeOfWorkPerformed, idTypeOfWork));
        model.put("typesInPerfomedWork", listTypesInPerfomedWorkService.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", listTypesInPerfomedWorkService.findById(updId));
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedService.findById(typeOfWorkPerformedFilter);
        model.put("typeOfWorkPerformedFilter", typeOfWorkPerformed == null ? 0 : typeOfWorkPerformed.getIdTypeOfWorkPerformed());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "listTypesInPerfomedWork";
    }

    @RequestMapping("/tables/update/listTypesInPerfomedWork")
    public String updateListTypesInPerfomedWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                                @RequestParam(name = "typeOfWorkPerformedFilter", required = false, defaultValue = "0") Integer typeOfWorkPerformedFilter,
                                                @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                                @RequestParam(name = "updIdTypeOfWorkPerformed", required = false, defaultValue = "") Integer updIdTypeOfWorkPerformed,
                                                @RequestParam(name = "updIdTypeOfWork", required = false, defaultValue = "") Integer updIdTypeOfWork,
                                                Map<String, Object> model) {
        model.put("message", listTypesInPerfomedWorkService.update(updId, updIdTypeOfWorkPerformed, updIdTypeOfWork));
        model.put("typesInPerfomedWork", listTypesInPerfomedWorkService.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedService.findById(typeOfWorkPerformedFilter);
        model.put("typeOfWorkPerformedFilter", typeOfWorkPerformed == null ? 0 : typeOfWorkPerformed.getIdTypeOfWorkPerformed());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "listTypesInPerfomedWork";
    }

    @RequestMapping("/tables/delete/listTypesInPerfomedWork")
    public String deleteListTypesInPerfomedWork(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                                @RequestParam(name = "typeOfWorkPerformedFilter", required = false, defaultValue = "0") Integer typeOfWorkPerformedFilter,
                                                @RequestParam(name = "typeOfWorkFilter", required = false, defaultValue = "0") Integer typeOfWorkFilter,
                                                @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                                Map<String, Object> model) {
        model.put("message", listTypesInPerfomedWorkService.delete(delId));
        model.put("typesInPerfomedWork", listTypesInPerfomedWorkService.findAll());
        model.put("typesOfWorkPerformed", typeOfWorkPerformedService.findAll());
        model.put("typesOfWork", typeOfWorkService.findAll());
        model.put("beanUp", listTypesInPerfomedWorkService.findById(updId));
        TypeOfWorkPerformed typeOfWorkPerformed = typeOfWorkPerformedService.findById(typeOfWorkPerformedFilter);
        model.put("typeOfWorkPerformedFilter", typeOfWorkPerformed == null ? 0 : typeOfWorkPerformed.getIdTypeOfWorkPerformed());
        TypeOfWork typeOfWork = typeOfWorkService.findById(typeOfWorkFilter);
        model.put("typeOfWorkFilter", typeOfWork == null ? 0 : typeOfWork.getIdTypeOfWork());
        return "listTypesInPerfomedWork";
    }

    /**
     * methods for changing the UserRegions table
     */
    @RequestMapping("/tables/userRegions")
    public String userRegions(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                              @RequestParam(name = "userFilter", required = false, defaultValue = "0") Integer userFilter,
                              @RequestParam(name = "regionFilter", required = false, defaultValue = "0") Integer regionFilter,
                              Map<String, Object> model) {
        model.put("usersRegions", userRegionsService.findAll());
        model.put("users", userService.findAllUser());
        model.put("regions", regionService.findAll());
        model.put("beanUp", userRegionsService.findById(updId));
        User user = userService.findById(userFilter);
        model.put("userFilter", user == null ? 0 : user.getIdusers());
        Region region = regionService.findById(regionFilter);
        model.put("regionFilter", region == null ? 0 : region.getIdRegion());
        return "userRegions";
    }

    @RequestMapping("/tables/add/userRegions")
    public String addUserRegions(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                 @RequestParam(name = "userFilter", required = false, defaultValue = "0") Integer userFilter,
                                 @RequestParam(name = "regionFilter", required = false, defaultValue = "0") Integer regionFilter,
                                 @RequestParam(name = "idUser", required = false, defaultValue = "") Integer idUser,
                                 @RequestParam(name = "idRegion", required = false, defaultValue = "") Integer idRegion,
                                 Map<String, Object> model) {
        model.put("message", userRegionsService.add(idUser, idRegion));
        model.put("usersRegions", userRegionsService.findAll());
        model.put("users", userService.findAllUser());
        model.put("regions", regionService.findAll());
        model.put("beanUp", userRegionsService.findById(updId));
        User user = userService.findById(userFilter);
        model.put("userFilter", user == null ? 0 : user.getIdusers());
        Region region = regionService.findById(regionFilter);
        model.put("regionFilter", region == null ? 0 : region.getIdRegion());
        return "userRegions";
    }

    @RequestMapping("/tables/update/userRegions")
    public String updateUserRegions(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                    @RequestParam(name = "userFilter", required = false, defaultValue = "0") Integer userFilter,
                                    @RequestParam(name = "regionFilter", required = false, defaultValue = "0") Integer regionFilter,
                                    @RequestParam(name = "updIdUser", required = false, defaultValue = "") Integer updIdUser,
                                    @RequestParam(name = "updIdRegion", required = false, defaultValue = "") Integer updIdRegion,
                                    Map<String, Object> model) {
        model.put("message", userRegionsService.update(updId, updIdUser, updIdRegion));
        model.put("usersRegions", userRegionsService.findAll());
        model.put("users", userService.findAllUser());
        model.put("regions", regionService.findAll());
        User user = userService.findById(userFilter);
        model.put("userFilter", user == null ? 0 : user.getIdusers());
        Region region = regionService.findById(regionFilter);
        model.put("regionFilter", region == null ? 0 : region.getIdRegion());
        return "userRegions";
    }

    @RequestMapping("/tables/delete/userRegions")
    public String deleteUserRegions(@RequestParam(name = "updId", required = false, defaultValue = "0") Integer updId,
                                    @RequestParam(name = "userFilter", required = false, defaultValue = "0") Integer userFilter,
                                    @RequestParam(name = "regionFilter", required = false, defaultValue = "0") Integer regionFilter,
                                    @RequestParam(name = "delId", required = false, defaultValue = "0") Integer delId,
                                    Map<String, Object> model) {
        model.put("message", userRegionsService.delete(delId));
        model.put("usersRegions", userRegionsService.findAll());
        model.put("users", userService.findAllUser());
        model.put("regions", regionService.findAll());
        model.put("beanUp", userRegionsService.findById(updId));
        User user = userService.findById(userFilter);
        model.put("userFilter", user == null ? 0 : user.getIdusers());
        Region region = regionService.findById(regionFilter);
        model.put("regionFilter", region == null ? 0 : region.getIdRegion());
        return "userRegions";
    }
}
