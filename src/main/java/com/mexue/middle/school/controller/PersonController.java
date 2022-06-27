package com.mexue.middle.school.controller;

import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.common.Result;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.service.PersonService;
import com.mexue.middle.school.util.Validator;
import com.mexue.middle.school.vo.PersonVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/mexue/person")
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    
    
    @GetMapping
    Map<String, Object> queryDemo(PersonSearch personSearch) {
        PageResult<PersonVo> pageResult = personService.query(personSearch);
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", pageResult.getCount());
        map.put("result", pageResult.getItems());
        map.put("success", true);
        return map;
    }
    
    @ApiOperation(value = "人员列表", notes = "分页获取人员列表")
    @GetMapping("/query")
    Result<PageResult<PersonVo>> query(PersonSearch personSearch) {
        return Result.OK(personService.query(personSearch));
    }
    
    /**
     * 列表查询
     *
     * @param personSearch
     * @return
     */
    @GetMapping("/list")
    Result<List<PersonVo>> queryList(PersonSearch personSearch) {
        List<PersonVo> pagerResult = personService.queryList(personSearch);
        return Result.OK(pagerResult);
    }
    
    /**
     * 新增/修改
     *
     * @return
     */
    @PostMapping("/save")
    Result<Integer> save(Person person) {
        //Asserts.isNotNull(person.getName(),ERROR_CODE.ERR_BANNER_TITLE_IS_NULL);
        Long id = person.getId();
        personService.save(person);
        Result<Integer> ok = Result.OK();
        ok.setMsg(Validator.isEmptyZero(id) ? "新增成功:id=" + person.getId() : "修改成功:id=" + person.getId());
        return ok;
    }
    
    /**
     * 删除内容
     * https://www.imooc.com/qadetail/268268
     * https://blog.csdn.net/qq_18671415/article/details/115704932
     * @param id
     * @return
     */
    @RequestMapping(value = {"delete/{id}", "delete"})
    @ResponseBody
    public Map delete(@PathVariable(value = "id", required = false) Integer id, HttpServletRequest request) {
        System.out.println("id1 = " + id);
        if (id == null) {
            id = Integer.parseInt(request.getParameter("id"));
            System.out.println("id2 = " + id);
        }
        personService.deleteByPrimaryKey(id);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("msg", "Id为" + id + "的记录删除成功");
        
        return map;
    }
    
    /**
     * 新增
     *
     * @return
     */
    @PostMapping
    Result<Integer> add(Person person) {
        //Asserts.isNotNull(person.getName(),ERROR_CODE.ERR_BANNER_TITLE_IS_NULL);
        System.out.println("person = " + person);
        int insertId = personService.insert(person);
        System.out.println("insertId = " + insertId);
        System.out.println("person = " + person);
        System.out.println("person = " + person.getId());
        return Result.OK(insertId);
    }
    
    /**
     * 修改
     *
     * @return
     */
    @PutMapping
    Result modify(@RequestBody Person person) {
        //Asserts.isNotNull(person.getId(), ERROR_CODE.ERR_BANNER_ID_IS_NULL);
        
        int i = personService.updateByPrimaryKeySelective(person);
        System.out.println("i = " + i);
        return Result.OK();
    }
    
    /**
     * 删除
     *
     * @return
     */
    @DeleteMapping("/{id}")
    Result remove(@PathVariable Integer id) {
        //Asserts.isNotNull(id, ERROR_CODE.ERR_BANNER_ID_IS_NULL);
        personService.deleteByPrimaryKey(id);
        return Result.OK();
    }
    
    
    /**
     * 单条查询
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Person> get(@PathVariable Integer id) {
        //Asserts.isNotNull(id, ERROR_CODE.ERR_BANNER_ID_IS_NULL);
        Person person = personService.selectByPrimaryKey(id);
        return Result.OK(person);
    }
    
    
}
