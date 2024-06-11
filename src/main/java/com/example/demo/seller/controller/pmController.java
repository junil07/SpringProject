package com.example.demo.seller.controller;

import com.example.demo.admin.security.SecurityServiceImple;
import com.example.demo.buyer.entity.Category;
import com.example.demo.buyer.entity.Stock;
import com.example.demo.buyer.service.CategoryService;
import com.example.demo.buyer.service.StockService;
import com.example.demo.seller.DTO.ProductDTO;
import com.example.demo.seller.DTO.ProductDetailDTO;
import com.example.demo.seller.domain.Product;
import com.example.demo.seller.domain.ProductImage;
import com.example.demo.seller.domain.ProductRelation;
import com.example.demo.seller.domain.Product_detail;
import com.example.demo.seller.service.ProductImageService;
import com.example.demo.seller.service.ProductRelationService;
import com.example.demo.seller.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/seller/pm")
public class pmController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private StockService stockService;
	@Autowired
	private ProductRelationService productRelationService;
	@Autowired
	private SecurityServiceImple securityService;

	public pmController() {

	}

	//상품관리 페이지 메핑
	//상품 조회
	@RequestMapping("inquiry")
	public String inquiry(Model model, @AuthenticationPrincipal User user, Principal principal) {

		// 판매자 - 판매자만 들어올 수 있게 ( 구매자 권한, 관리자 권한을 갖고 있는 세션(비로그인 포함)은 들어오지 못함 )
		// 판매자 페이지는 어차피 전부 판매자 권한이 필요한 곳이라서 구매자 페이지처럼 로그인 안한 사람도 들어갈 곳이 있는게 아니기 때문에 전부 로그인이 필요함
		String sellerId = "";
		String alert = "로그인이 필요합니다.";

		if (principal != null) {
			if (securityService.hasRole(user, "ROLE_SELLER")) { // 현 세션의 user 객체가 '판매자'권한을 갖고 있다면
				sellerId = user.getUsername();
				alert = "";
				model.addAttribute("sellerId", user.getUsername()); // 판매자 아이디를 저장
				List<Product> product = productService.getProductList(user.getUsername());

				model.addAttribute("product", product);


			} else { // 여기는 판매자 권한이 없을 때,
				alert = "판매자가 아니면 접근할 수 없습니다.";
				return "/seller/login";
			}
		}

		model.addAttribute("alertMessage", alert);
		System.out.println(alert);

		return "/seller/pm/inquiry";
	}

	//상품 등록 페이지
	@GetMapping("registration")
	public String registration(Model model, @AuthenticationPrincipal User user, Principal principal) {
		List<Category> categories = categoryService.getAll();
		model.addAttribute("category", categories);
		//userId 세팅
		ProductDTO productDTO = new ProductDTO();
		productDTO.setSellerId(user.getUsername());
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("productDetailDTO", new ProductDetailDTO());
		return "/seller/pm/registration";
	}

	//연관상품 등록
	@RequestMapping("relation")
	public String relation(Model model, @AuthenticationPrincipal User user, Principal principal) {
		List<Product> product = productService.getProductList(user.getUsername());
		model.addAttribute("product", product);
		//RelationProduct One 중복없이 뽑음
		List<ProductRelation> productRelationList = productRelationService.getRelationProductIdList(user.getUsername());
		model.addAttribute("relationProductList", productRelationList);
		return "/seller/pm/relation";
	}

	//배송정보 관리
	@RequestMapping("deliver")
	public String deliver(Model model, @AuthenticationPrincipal User user, Principal principal) {
		return "/seller/pm/deliver";
	}

	//상품 상세페이지 이동
	@GetMapping("/{productId}")
	public String getProductDetail(@PathVariable("productId") Integer productId, Model model, @AuthenticationPrincipal User user, Principal principal) {
		//상품
		Product product = productService.getProduct(productId);
		ProductDTO productDTO = productService.getProductDTO(product);
		model.addAttribute("productDTO", productDTO);
		//상품 상세
		Product_detail productDetail = productService.getProductDetail(product);
		ProductDetailDTO productDetailDTO = productService.getProductDetailDTO(productDetail);
		//카테고리
		model.addAttribute("productDetailDTO", productDetailDTO);
		List<Category> categories = categoryService.getAll();
		model.addAttribute("category", categories);
		//이미지
		ProductImage productImage = productImageService.getProductImage(product);
		model.addAttribute("productImage", productImage);
		model.addAttribute("getProductImage", "/assets/image/pMain/" + productImage.getProductImageSname() + productImage.getProductImageExtension());
		//해시태그 객체로 저장
		model.addAttribute("hashtagList", productDTO.getProductHashtag());
		//상품 재고
		List<Stock> stocks = stockService.getStockList(productId);
		model.addAttribute("stockList", stocks);
		// 저장된 카테고리 불러오기
		Category subSubCategory = product.getCategory();
		model.addAttribute("ssubSubCategory", subSubCategory);
		Category subCategory = categoryService.getCategory(subSubCategory.getCategoryParentId());
		model.addAttribute("ssubCategory", subCategory);
		Category sCategory = categoryService.getCategory(subCategory.getCategoryParentId());
		model.addAttribute("sCategory", sCategory);
		return "/seller/pm/product_detail"; // Thymeleaf 템플릿의 경로
	}

	//서브 카테고리
	@GetMapping("/proc/subcategories/{categoryId}")
	@ResponseBody
	public List<Category> getSubcategories(@PathVariable("categoryId") Long parentId) {
		// categoryId를 이용하여 해당 카테고리의 하위 카테고리를 가져옴
		List<Category> subcategories = categoryService.getSubCategories(parentId);
		return subcategories;
	}

	//서브-서브 카테고리
	@GetMapping("/proc/subSubcategories/{categoryId}")
	@ResponseBody
	public List<Category> getSubSubcategories(@PathVariable("categoryId") Long parentId) {
		// categoryId를 이용하여 해당 카테고리의 하위 카테고리를 가져옴
		List<Category> subSubcategories = categoryService.getSubCategories(parentId);
		return subSubcategories;
	}

	//저장
	@PostMapping("/products")
	public String saveProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
							  @ModelAttribute("productDetailDTO") ProductDetailDTO productDetailDTO,
							  @RequestParam("totalHash") List<String> totalHash,
							  @RequestParam("totalStock") List<Object> totalStock,
							  @RequestParam("file") MultipartFile file,
							  @AuthenticationPrincipal User user, Principal principal) {
		//해시태그 추가
		productDTO.setProductHashtag(totalHash);
		//코드 자동지정
		productDTO.setProductCode(productService.getCode(user.getUsername()));
		//상품 저장
		productDTO.setSellerId(user.getUsername());
		productService.addProduct(productDTO);

		Product product = productService.getProductByCode(productDTO.getProductCode());
		//나중에 이미지를 위한 productId 설정
		productDTO.setProductId(product.getProductId());
		productDetailDTO.setProductId(product.getProductId());

		//상품 상세정보 추가
		productService.addProductDetail(productDetailDTO);
		//상품 이미지 추가
		productImageService.addProductImage(file, productDTO);
		//재고 추가
		List<List<Object>> newStocks = stockService.getNewStockList(totalStock);
		stockService.stockResult(newStocks, product.getProductId());
		return "redirect:/seller/pm/inquiry"; // 파일 업로드 성공 시 리다이렉트할 뷰 이름
	}

	@PostMapping("/productsUpdate")
	public String updateProduct(@ModelAttribute("productDTO") ProductDTO productDTO,
							  @ModelAttribute("productDetailDTO") ProductDetailDTO productDetailDTO,
								@ModelAttribute("newCategoryId") Long categoryId,
								@RequestParam("totalHash") List<String> totalHash,
							  @RequestParam("totalStock") List<Object> totalStock,
							  @RequestParam("file") MultipartFile file,
								RedirectAttributes redirectAttributes, Model model,
								@AuthenticationPrincipal User user, Principal principal) {

		long productId = productDTO.getProductId();
		redirectAttributes.addAttribute("productId", productId);
		productDTO.setCategoryId(categoryId);
		productDTO.setProductHashtag(totalHash);

		//재고 파트
		List<List<Object>> newStocks = stockService.getNewStockList(totalStock);
		stockService.stockResult(newStocks, productId);

		productDTO.setSellerId(user.getUsername());
		productService.addProduct(productDTO);
		productService.addProductDetail(productDetailDTO);

		if(!Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
			System.out.println("null 아님");
			ProductImage productImage = productImageService.getProductImage(productService.getProduct(productId));

			//기존 파일 삭제
			productImageService.delProductImage(productImage);
			//파일 추가
			productImageService.addProductImage(file, productDTO);
		}
		return "redirect:/seller/pm/{productId}"; // 파일 업로드 성공 시 리다이렉트할 뷰 이름
	}

	//상품 삭제
	@PostMapping("/del")
	public String delProduct(@ModelAttribute("productDTO") ProductDTO productDTO) {

		long productId = productDTO.getProductId();
		ProductImage productImage = productImageService.getProductImage(productService.getProduct(productId));
		productImageService.delProductImage(productImage);
		productService.delProduct(productId);

		System.out.println(productId);

		return "redirect:/seller/pm/inquiry"; // 파일 업로드 성공 시 리다이렉트할 뷰 이름
	}

	//연관상품 추가 & 수정
	@PostMapping("/relationProduct")
	public String relationProduct(@ModelAttribute("relationName") String relationName,
								  //연관상품 리스트의 번호
								  @ModelAttribute("relationNum") String relationNum,
								  @RequestParam("relationList") List<String> relationList,
								  @AuthenticationPrincipal User user, Principal principal) {

		//product의 code 값을 바꿈
		productRelationService.saveRelationProduct(relationName, relationNum, relationList, user.getUsername());

		return "redirect:/seller/pm/relation"; // 파일 업로드 성공 시 리다이렉트할 뷰 이름
	}

	//연관상품 리스트 선택
	@GetMapping("/getRelationProductIdList")
	public ResponseEntity<List<Product>> getListByProductId(@RequestParam("pk") long pk) {
		// productId 사용하여 가장 상위 productRelation 객체 가져옴
		ProductRelation productRelation = productRelationService.getRelationProduct(pk);
		// 위의 객체를 통해 해당 Relation의 One 값 가져옴
		int one = productRelation.getProductRelationOne();
		List<ProductRelation> productRelationslist = productRelationService.getRelationProductList(one);
		//product로 변환
		List<Product> productList = productRelationService.convertProductList(productRelationslist);

		// 가져온 List를 클라이언트에 반환
		return ResponseEntity.ok().body(productList);
	}

	//연관상품 삭제
	@PostMapping("/delRelation")
	public String delRelation (@ModelAttribute("relationNum") String productRelationCode,
							   @AuthenticationPrincipal User user, Principal principal) {
		//삭제
		productRelationService.delRelationProduct(productRelationCode, user.getUsername());
		return "redirect:/seller/pm/relation"; // 파일 업로드 성공 시 리다이렉트할 뷰 이름
	}





}







