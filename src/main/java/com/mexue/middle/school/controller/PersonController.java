package com.mexue.middle.school.controller;

import com.mexue.middle.school.common.PageResult;
import com.mexue.middle.school.common.Result;
import com.mexue.middle.school.entity.Person;
import com.mexue.middle.school.search.PersonSearch;
import com.mexue.middle.school.service.PersonService;
import com.mexue.middle.school.vo.PersonVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 新增
     *
     * @return
     */
    @PostMapping
    Result<Integer> add(@RequestBody Person person) {
        //Asserts.isNotNull(person.getName(),ERROR_CODE.ERR_BANNER_TITLE_IS_NULL);
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
