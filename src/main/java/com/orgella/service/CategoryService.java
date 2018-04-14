package com.orgella.service;

import com.orgella.model.Category;
import com.orgella.model.Node;
import com.orgella.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    private Node<Category> rootCategory;
    private List<Integer> idList;
    private Map<Integer, Node<Category>> categoryMap = new HashMap<>();

    public Node<Category> getBuilder() {
        return rootCategory;
    }

    public List<Category> findAllBy(){
        return categoryRepository.findAllBy();
    }

    public List<Category> findCategoriesByParentId(Integer parentId){
        return categoryRepository.findCategoriesByParentId(parentId);
    }

    public Category findCategoryById(Integer id){
        return categoryRepository.findCategoryById(id);
    }

    public Boolean isParentExist(Integer id){
        return categoryRepository.existsCategoryById(id);
    }

    public void save(Category category){
        categoryRepository.save(category);
    }

    public Node<Category> createCategory() {

        rootCategory = new Node<Category>(null, new Category());
        rootCategory = getRootCategories();

        for (Node<Category> categoryNode : rootCategory.getChildren()) {
            categoryNode.addChildren(getChildrenByParentId(categoryNode.getItem().getIdCategory()));
        }

        //rootCategory.getChildren().stream()
        //        .forEach(n -> n.addChildren(getChildrenByParentId(n.getItem().getIdCategory())));

        return rootCategory;
    }

    public boolean addCategory(String parentCategory, String name) {

        if (findParentByName(parentCategory)) {
            Integer parentId = categoryRepository.findCategoryByName(parentCategory).getIdCategory();
            categoryRepository.save(new Category(name, parentId));
            return true;
        }

        return false;
    }

    public List<Integer> getCategoryAndSubcategoriesListId(int categoryId){

        if(isParentExist(categoryId)){
            Node<Category> nodeTemp = findParentById(categoryId);

            idList = new ArrayList<>();
            idList.add(nodeTemp.getItem().getIdCategory());

            for (Node<Category> categoryNode : nodeTemp.getChildren()) {
                idList.add(categoryNode.getItem().getIdCategory());
            }
        }

        return idList;
    }

    private Node<Category> findParentById(Integer parentId) {
        categoryMap = copyCategoryToMap();

        return categoryMap.get(parentId);
//        Category category = categoryRepository.findCategoryById(parentId);
//        Node<Category> node = new Node<>(category);
//
//        return node;
    }

    private boolean findParentByName(String parentName) {

        return categoryRepository.existsCategoryByName(parentName);
    }

    private Map<Integer, Node<Category>> copyCategoryToMap() {

        List<Node<Category>> nodeList = rootCategory.getChildren();

        writeToMap(nodeList);

        return categoryMap;
    }

    private void writeToMap(List<Node<Category>> lists) {

        if (!lists.isEmpty()) {
            for (Node<Category> categoryNode : lists) {
                categoryMap.put(categoryNode.getItem().getIdCategory(), categoryNode);

                writeToMap(categoryNode.getChildren());
            }
        }
    }

    private int returnLastChildOfParent(Node<Category> parentNode) {
        return parentNode.getChildren().size()-1;
    }

    public Node<Category> getRootCategories(){

        List<Category> tempCategoryList = new ArrayList<>();
        rootCategory = new Node<Category>(null, new Category());

        tempCategoryList = findCategoriesByParentId(0);

        for (Category category : tempCategoryList) {
            rootCategory.addChild(category);
        }

        return rootCategory;
    }

    private List<Node> getChildrenByParentId(int parentId) {

        List<Category> tempCategoryList = new ArrayList<>();
        List<Node> nodeList = new ArrayList<>();

        tempCategoryList = findCategoriesByParentId(parentId);

        for (Category category : tempCategoryList) {
            nodeList.add(new Node<Category>(category));
        }

        return nodeList;
    }


}
