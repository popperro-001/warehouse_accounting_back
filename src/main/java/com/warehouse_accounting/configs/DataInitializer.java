package com.warehouse_accounting.configs;

import com.warehouse_accounting.models.TypeOfAdjustment;
import com.warehouse_accounting.models.TypeOfPayment;
import com.warehouse_accounting.models.dto.AdjustmentDto;
import com.warehouse_accounting.models.dto.BankAccountDto;
import com.warehouse_accounting.models.dto.CallDto;
import com.warehouse_accounting.models.dto.CompanyDto;
import com.warehouse_accounting.models.dto.ContractDto;
import com.warehouse_accounting.models.dto.ContractorDto;
import com.warehouse_accounting.models.dto.ContractorGroupDto;
import com.warehouse_accounting.models.dto.DepartmentDto;
import com.warehouse_accounting.models.dto.EmployeeDto;
import com.warehouse_accounting.models.dto.ImageDto;
import com.warehouse_accounting.models.dto.LegalDetailDto;
import com.warehouse_accounting.models.dto.PaymentDto;
import com.warehouse_accounting.models.dto.PositionDto;
import com.warehouse_accounting.models.dto.ProductDto;
import com.warehouse_accounting.models.dto.ProjectDto;
import com.warehouse_accounting.models.dto.RoleDto;
import com.warehouse_accounting.models.dto.TaskDto;
import com.warehouse_accounting.models.dto.TechnologicalMapDto;
import com.warehouse_accounting.models.dto.TechnologicalMapGroupDto;
import com.warehouse_accounting.models.dto.TechnologicalMapMaterialDto;
import com.warehouse_accounting.models.dto.TechnologicalMapProductDto;
import com.warehouse_accounting.models.dto.TechnologicalOperationDto;
import com.warehouse_accounting.models.dto.TypeOfContractorDto;
import com.warehouse_accounting.models.dto.TypeOfPriceDto;
import com.warehouse_accounting.models.dto.UnitDto;
import com.warehouse_accounting.services.interfaces.AdjustmentService;
import com.warehouse_accounting.services.interfaces.BankAccountService;
import com.warehouse_accounting.services.interfaces.CallService;
import com.warehouse_accounting.services.interfaces.CompanyService;
import com.warehouse_accounting.services.interfaces.ContractService;
import com.warehouse_accounting.services.interfaces.ContractorGroupService;
import com.warehouse_accounting.services.interfaces.ContractorService;
import com.warehouse_accounting.services.interfaces.DepartmentService;
import com.warehouse_accounting.services.interfaces.EmployeeService;
import com.warehouse_accounting.services.interfaces.ImageService;
import com.warehouse_accounting.services.interfaces.LegalDetailService;
import com.warehouse_accounting.services.interfaces.PaymentService;
import com.warehouse_accounting.services.interfaces.PositionService;
import com.warehouse_accounting.services.interfaces.ProductService;
import com.warehouse_accounting.services.interfaces.ProjectService;
import com.warehouse_accounting.services.interfaces.RoleService;
import com.warehouse_accounting.services.interfaces.TaskService;
import com.warehouse_accounting.services.interfaces.TechnologicalMapGroupService;
import com.warehouse_accounting.services.interfaces.TechnologicalMapMaterialService;
import com.warehouse_accounting.services.interfaces.TechnologicalMapProductService;
import com.warehouse_accounting.services.interfaces.TechnologicalMapService;
import com.warehouse_accounting.services.interfaces.TechnologicalOperationService;
import com.warehouse_accounting.services.interfaces.TypeOfContractorService;
import com.warehouse_accounting.services.interfaces.TypeOfPriceService;
import com.warehouse_accounting.services.interfaces.UnitService;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Log4j2
@Component
public class DataInitializer {

    @Value("${data-init.unit-data}")
    private File unit_init_file;

    private final RoleService roleService;
    private final UnitService unitService;
    private final ProductService productService;
    private final TechnologicalMapService technologicalMapService;
    private final TechnologicalMapGroupService technologicalMapGroupService;
    private final TechnologicalMapMaterialService technologicalMapMaterialService;
    private final TechnologicalMapProductService technologicalMapProductService;
    private final TechnologicalOperationService technologicalOperationService;
    private final TaskService taskService;
    private final CallService callService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final ImageService imageService;
    private final PositionService positionService;
    private final ContractorService contractorService;
    private final ContractorGroupService contractorGroupService;
    private final TypeOfPriceService typeOfPriceService;
    private final BankAccountService bankAccountService;
    private final LegalDetailService legalDetailService;
    private final TypeOfContractorService typeOfContractorService;
    private final AdjustmentService adjustmentService;
    private final ProjectService projectService;
    private final CompanyService companyService;
    private final ContractService contractService;
    private final PaymentService paymentService;

    public DataInitializer(RoleService roleService,
                           UnitService unitService,
                           ProductService productService,
                           TechnologicalMapService technologicalMapService,
                           TechnologicalMapGroupService technologicalMapGroupService,
                           TechnologicalMapMaterialService technologicalMapMaterialService,
                           TechnologicalMapProductService technologicalMapProductService,
                           TechnologicalOperationService technologicalOperationService,
                           TaskService taskService,
                           CallService callService,
                           EmployeeService employeeService,
                           DepartmentService departmentService,
                           ImageService imageService,
                           PositionService positionService,
                           ContractorService contractorService,
                           ContractorGroupService contractorGroupService,
                           TypeOfPriceService typeOfPriceService,
                           BankAccountService bankAccountService,
                           LegalDetailService legalDetailService,
                           TypeOfContractorService typeOfContractorService,
                           AdjustmentService adjustmentService,
                           ProjectService projectService,
                           CompanyService companyService,
                           ContractService contractService,
                           PaymentService paymentService) {
        this.roleService = roleService;
        this.unitService = unitService;
        this.productService = productService;
        this.technologicalMapService = technologicalMapService;
        this.technologicalMapGroupService = technologicalMapGroupService;
        this.technologicalMapMaterialService = technologicalMapMaterialService;
        this.technologicalMapProductService = technologicalMapProductService;
        this.technologicalOperationService = technologicalOperationService;
        this.taskService = taskService;
        this.callService = callService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.imageService = imageService;
        this.positionService = positionService;
        this.contractorService = contractorService;
        this.contractorGroupService = contractorGroupService;
        this.typeOfPriceService = typeOfPriceService;
        this.bankAccountService = bankAccountService;
        this.legalDetailService = legalDetailService;
        this.typeOfContractorService = typeOfContractorService;
        this.adjustmentService = adjustmentService;
        this.projectService = projectService;
        this.companyService = companyService;
        this.contractService = contractService;
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void init() {
        initRoles();
        initUnits();
        initProduct();
        initTechnologicalMap();
        initTechnologicalOperation();
        initDepartment();
        initImage();
        initPosition();
        initEmployees();
        initContractorGroup();
        initTypeOfContractor();
        initLegalDetail();
        initBankAccount();
        initTypeOfPrice();
        initContractors();
        initCalls();
        initTask();
        initProject();
        initCompany();
        initAdjustments();
        initContract();
        initPayment();
    }

    private void initRoles() {
        roleService.create(RoleDto.builder()
                .name("admin")
                .sortNumber("sort_admin")
                .build()
        );

        roleService.create(RoleDto.builder()
                .name("user")
                .sortNumber("sort_user")
                .build()
        );
    }

    public void initUnits() {
        try (FileInputStream fileInputStream = new FileInputStream(unit_init_file)) {
            XSSFSheet sheet = new XSSFWorkbook(fileInputStream).getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                if (currentRow.getRowNum() == 0) {
                    currentRow = rowIterator.next();
                }
                unitService.create(UnitDto.builder()
                        .shortName(currentRow.getCell(0).getStringCellValue())
                        .fullName(currentRow.getCell(1).getStringCellValue())
                        .sortNumber(String.valueOf((long) currentRow.getCell(2).getNumericCellValue()))
                        .build());
            }
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Units", e);
        }
    }

    public void initProduct() {
        try {
            productService.create(
                    ProductDto.builder()
                            .id(1L)
                            .name("????????")
                            .build()
            );

            productService.create(
                    ProductDto.builder()
                            .id(2L)
                            .name("?????? ?? ????????????")
                            .build()
            );

            productService.create(
                    ProductDto.builder()
                            .id(3L)
                            .name("?????????????? ????????????????????")
                            .build()
            );

            productService.create(
                    ProductDto.builder()
                            .id(4L)
                            .name("??????????????????")
                            .build()
            );

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Product", e);
        }
    }

    private void initTechnologicalMap() {
        try {
            technologicalMapGroupService.create(
                    TechnologicalMapGroupDto.builder()
                            .name("???????????? 1")
                            .code("????1????1")
                            .comment("?????????? ???????????? ????????????")
                            .parentTechnologicalMapGroupId(null)
                            .build()
            );
            technologicalMapGroupService.create(
                    TechnologicalMapGroupDto.builder()
                            .name("???????????? 2")
                            .code("????2????1")
                            .comment("?????? ???????? ????????????")
                            .parentTechnologicalMapGroupId(null)
                            .build()
            );
            technologicalMapGroupService.create(
                    TechnologicalMapGroupDto.builder()
                            .id(3L)
                            .name("???????????? 1-1")
                            .code("????1????1_??1")
                            .comment("???? ???? ?????????????? ???????????? ????????????")
                            .parentTechnologicalMapGroupId(1L)
                            .build()
            );
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? TechnologicalMapGroup", e);
        }

        try {
            technologicalMapService.create(
                    TechnologicalMapDto.builder()
                            .id(1L)
                            .name("???????????????????????? ??????????????????")
                            .productionCost(BigDecimal.valueOf(100500))
                            .isArchived(false)
                            .comment("?????????????????? ???????????? ???????????????????????? ??????????????????")
                            .materials(null)
                            .finishedProducts(null)
                            .technologicalMapGroupId(1L)
                            .build()
            );

            technologicalMapService.create(
                    TechnologicalMapDto.builder()
                            .name("???????????????????? - ?????????? ?????? ?? ????????????????")
                            .productionCost(BigDecimal.valueOf(0))
                            .isArchived(false)
                            .comment("???????????? ?? ?????????? - ???????? ?? ????????????????")
                            .materials(null)
                            .finishedProducts(null)
                            .technologicalMapGroupId(2L)
                            .build()
            );

            TechnologicalMapDto technologicalMap = technologicalMapService.getById(1L);

            List<TechnologicalMapMaterialDto> materialDtos = new ArrayList<>();
            materialDtos.add(new TechnologicalMapMaterialDto().builder()
                    .materialId(productService.getById(1L).getId())
                    .materialName(productService.getById(1L).getName())
                    .count(BigDecimal.valueOf(2))
                    .technologicalMapDto(technologicalMap)
                    .build());
            materialDtos.add(new TechnologicalMapMaterialDto().builder()
                    .materialId(productService.getById(2L).getId())
                    .materialName(productService.getById(2L).getName())
                    .count(BigDecimal.valueOf(1))
                    .technologicalMapDto(technologicalMap)
                    .build());
            materialDtos.add(new TechnologicalMapMaterialDto().builder()
                    .materialId(productService.getById(3L).getId())
                    .materialName(productService.getById(3L).getName())
                    .count(BigDecimal.valueOf(1))
                    .technologicalMapDto(technologicalMap)
                    .build());
            materialDtos.forEach(technologicalMapMaterialService::create);

            List<TechnologicalMapProductDto> productDtos = new ArrayList<>();
            productDtos.add(new TechnologicalMapProductDto().builder()
                    .finishedProductId(productService.getById(4L).getId())
                    .finishedProductsName(productService.getById(4L).getName())
                    .count(BigDecimal.valueOf(1))
                    .technologicalMapDto(technologicalMap)
                    .build());
            productDtos.forEach(technologicalMapProductService::create);

            technologicalMap.setMaterials(materialDtos);
            technologicalMap.setFinishedProducts(productDtos);

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? TechnologicalMap", e);
        }
    }

    private void initTechnologicalOperation() {
        try {

            technologicalOperationService.create(
                    TechnologicalOperationDto.builder()
                            .id(1L)
                            .number("????-1")
                            .technologicalOperationDateTime(LocalDateTime.now())
                            .technologicalMapId(technologicalMapService.getById(1L).getId())
                            .technologicalMapName(technologicalMapService.getById(1L).getName())
                            .volumeOfProduction(BigDecimal.valueOf(100))
                            .warehouseForMaterialsId(1L)
                            .warehouseForMaterialsName("???????????????? ??????????")
                            .warehouseForProductId(1L)
                            .warehouseForProductName("???????????????? ??????????")
                            .build());


            technologicalOperationService.create(
                    TechnologicalOperationDto.builder()
                            .id(2L)
                            .number("????-2")
                            .technologicalOperationDateTime(LocalDateTime.now().minusDays(1))
                            .technologicalMapId(technologicalMapService.getById(1L).getId())
                            .technologicalMapName(technologicalMapService.getById(1L).getName())
                            .volumeOfProduction(BigDecimal.valueOf(100))
                            .warehouseForMaterialsId(1L)
                            .warehouseForMaterialsName("???????????????? ??????????")
                            .warehouseForProductId(1L)
                            .warehouseForProductName("???????????????? ??????????")
                            .build());

            TechnologicalOperationDto operationDto = technologicalOperationService.getById(2L);
            List<TaskDto> taskDtos = new ArrayList<>();
            taskDtos.add(new TaskDto().builder()
                    .description("1# ???????????? ?????????? ?????? ?????? ???????????????? 2")
                    .deadline(LocalDateTime.now().plusDays(1))
                    .dateOfCreation(LocalDateTime.now())
                    .documentId(operationDto.getId())
                    .build());
            taskDtos.add(new TaskDto().builder()
                    .description("2# ???????????? ?????????? ?????? ?????? ???????????????? 2")
                    .deadline(LocalDateTime.now().plusDays(2))
                    .dateOfCreation(LocalDateTime.now())
                    .documentId(operationDto.getId())
                    .build());
            taskDtos.forEach(taskService::create);

            operationDto.setTasks(taskDtos);

            List<TaskDto> taskDtos3 = new ArrayList<>();
            taskDtos3.add(new TaskDto().builder()
                    .description("3# ???????????? ?????????? ?????? ?????? ???????????????? 3")
                    .deadline(LocalDateTime.now().plusDays(1))
                    .dateOfCreation(LocalDateTime.now())
                    .build());
            taskDtos3.add(new TaskDto().builder()
                    .description("4# ???????????? ?????????? ?????? ?????? ???????????????? 3")
                    .deadline(LocalDateTime.now().plusDays(2))
                    .dateOfCreation(LocalDateTime.now())
                    .documentId(3L)
                    .build());
            //taskDtos3.forEach(taskService::create);

            technologicalOperationService.create(
                    TechnologicalOperationDto.builder()
                            .id(3L)
                            .number("????-3")
                            .technologicalOperationDateTime(LocalDateTime.now().minusDays(5))
                            .technologicalMapId(technologicalMapService.getById(1L).getId())
                            .technologicalMapName(technologicalMapService.getById(1L).getName())
                            .volumeOfProduction(BigDecimal.valueOf(100))
                            .warehouseForMaterialsId(1L)
                            .warehouseForMaterialsName("???????????????? ??????????")
                            .warehouseForProductId(1L)
                            .warehouseForProductName("???????????????? ??????????")
                            .tasks(taskDtos3)
                            .build());

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? TechnologicalOperation", e);
        }
    }

    private void initTask() {
        try {
            taskService.create(TaskDto.builder()
                    .description("???????????? ???????????? ????????????")
                    .deadline(LocalDateTime.now().plusDays(3))
                    .dateOfCreation(LocalDateTime.now())
                    .build());

            taskService.create(TaskDto.builder()
                    .description("???????????? ???????????? ?? ???????????????????? ?????? ???????????????? 1")
                    .deadline(LocalDateTime.now().plusDays(3))
                    .dateOfCreation(LocalDateTime.now())
                    .documentId(1L)
                    .contractorId(1L)
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Tasks", e);
        }
    }

    private void initDepartment() {
        try {
            departmentService.create(DepartmentDto.builder()
                    .id(1L)
                    .name("departmentName")
                    .sortNumber("sortNumber")
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Department", e);
        }
    }

    private void initPosition() {
        try {
            positionService.create(PositionDto.builder()
                    .id(1L)
                    .name("departmentName")
                    .sortNumber("sortNumber")
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Positions", e);
        }
    }

    private void initImage() {
        try {
            imageService.create(ImageDto.builder()
                    .id(1L)
                    .imageUrl("imageUrl")
                    .sortNumber("sortNumber")
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Images", e);
        }
    }

    private void initEmployees() {
        try {
            employeeService.create(EmployeeDto.builder()
                    .id(1L)
                    .lastName("lastName")
                    .firstName("firstName")
                    .middleName("middleName")
                    .sortNumber("sortNumber")
                    .phone("phone")
                    .inn("inn")
                    .description("description")
                    .email("some@mail.ru")
                    .password("password")
                    .department(departmentService.getById(1L))
                    .position(positionService.getById(1L))
                    .image(imageService.getById(1L))
                    .roles(Collections.emptySet())
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Employees", e);
        }
    }


    private void initCalls() {
        try {
            callService.create(CallDto.builder()
                    .id(1L)
                    .callTime(null)
                    .type("someType")
                    .number(1234567890L)
                    .callDuration(100L)
                    .comment("comment")
                    .callRecord("callRecord")
                    .whenChanged(null)
                    .contractorName(null)
                    .contractorId(null)
                    .employeeWhoChangedName("null")
                    .employeeWhoChangedId(1L)
                    .employeeWhoCalledName("null")
                    .employeeWhoCalledId(1L)
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Calls", e);
        }
    }

    private void initContractors() {
        try {
            List<TaskDto> taskDtos = new ArrayList<>();
            taskDtos.add(taskService.getById(2L));
            taskDtos.add(taskService.getById(1L));

            contractorService.create(ContractorDto.builder()
                    .id(1L)
                    .name("first_Contractor")
                    .contractorGroupId(1L)
                    .typeOfPriceId(1L)
                    .bankAccountDtos(Collections.EMPTY_LIST)
                    .legalDetailDto(legalDetailService.getById(1L))
                    .taskDtos(taskDtos)
                    .build());
            contractorService.getById(1L).setTaskDtos(taskDtos);
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? Contractors", e);
        }
    }

    private void initContractorGroup() {
        try {
            contractorGroupService.create(ContractorGroupDto.builder()
                    .id(1L)
                    .name("first_ContractorGroup")
                    .build());

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? contractor_grous", e);
        }
    }

    private void initTypeOfPrice() {
        try {
            typeOfPriceService.create(TypeOfPriceDto.builder()
                    .id(1L)
                    .name("firstTypeOfPrice")
                    .build());

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? type_of_prices", e);
        }
    }

    private void initBankAccount() {
        try {
            bankAccountService.create(BankAccountDto.builder()
                    .id(1L)
//                    .name("first_ContractorGroup")
                    .build());

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? bank_accounts", e);
        }
    }

    private void initLegalDetail(){
        try {
            legalDetailService.create(LegalDetailDto.builder()
                    .id(1L)
                    .fullName("firstFullName")
                    .typeOfContractorId(1L)
                    .build());

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? legal_details", e);
        }
    }

    private void initTypeOfContractor() {
        try {
            typeOfContractorService.create(TypeOfContractorDto.builder()
                    .id(1L)
                    .name("firstTypeOfContractorName")
                    .build());

        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? type_of_contractors", e);
        }
    }

    private void initAdjustments() {

        adjustmentService.create(AdjustmentDto.builder()
                .id(1L)
                .number("00001")
                .dateTimeAdjustment(LocalDateTime.now())
                .companyId(1L)
                .companyName("??????????????????????1")
                .contractorId(1L)
                .contractorName("first_Contractor")
                .type(TypeOfAdjustment.ACCOUNTBALANCE)
                .currentBalance(BigDecimal.valueOf(1000.00))
                .totalBalance(BigDecimal.valueOf(0.00))
                .adjustmentAmount(BigDecimal.valueOf(1000.00))
                .comment("1 ??????????????????????????")
                .when??hanged(LocalDateTime.now())
                .build());

        adjustmentService.create(AdjustmentDto.builder()
                .id(2L)
                .number("00002")
                .dateTimeAdjustment(LocalDateTime.now())
                .companyId(1L)
                .companyName("??????????????????????1")
                .contractorId(1L)
                .contractorName("first_Contractor")
                .type(TypeOfAdjustment.CASHBALANCE)
                .currentBalance(BigDecimal.valueOf(1000.00))
                .totalBalance(BigDecimal.valueOf(2000.00))
                .adjustmentAmount(BigDecimal.valueOf(3000.00))
                .comment("2 ??????????????????????????")
                .when??hanged(LocalDateTime.now())
                .build());

        adjustmentService.create(AdjustmentDto.builder()
                .id(3L)
                .number("00003")
                .dateTimeAdjustment(LocalDateTime.now())
                .companyId(1L)
                .companyName("??????????????????????1")
                .contractorId(1L)
                .contractorName("first_Contractor")
                .type(TypeOfAdjustment.COUNTERPARTY)
                .currentBalance(BigDecimal.valueOf(2000.00))
                .totalBalance(BigDecimal.valueOf(500.00))
                .adjustmentAmount(BigDecimal.valueOf(1500.00))
                .comment("3 ??????????????????????????")
                .when??hanged(LocalDateTime.now())
                .build());
    }
    private void initPayment() {
        try {
            ContractorDto contractorDto = contractorService.getById(1L);
            CompanyDto companyDto = companyService.getById(1L);
            List<TaskDto> taskDtos = new ArrayList<>();
            taskDtos.add(taskService.getById(1L));
            taskDtos.add(taskService.getById(2L));

            paymentService.create(PaymentDto.builder()
                    .id(1L)
                    .number("1")
                    .amount(BigDecimal.valueOf(10L))
                    .tax(BigDecimal.valueOf(10L))
                    .typeOfPayment(TypeOfPayment.INCOMING_PAYMENT)
                    .projectId(1L)
                    .projectName("????????????")
                    .contractId(1L)
                    .contractNumber("1")
                    .contractorDto(contractorDto)
                    .companyDto(companyDto)
                    .purpose("????????")
                    .isDone(true)
                    .comment("??????????????????????")
                    .documents(new ArrayList<>())
                    .taskDtos(taskDtos)
                    .paymentExpenditureId(1L)
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? payments", e);
        }
    }

    private void initCompany() {
        try {
            LegalDetailDto legalDetailDto = legalDetailService.getById(1L);
            companyService.create(CompanyDto.builder()
                    .id(1L)
                    .name("??????????????????????1")
                    .address("????????????1")
                    .legalDetailDto(legalDetailDto)
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? companies", e);
        }
    }

    private void initContract() {
        try {
            BankAccountDto bankAccountDto = bankAccountService.getById(1L);
            ContractorDto contractorDto = contractorService.getById(1L);
            LegalDetailDto legalDetailDto = legalDetailService.getById(1L);
            CompanyDto companyDto = companyService.getById(1L);

            contractService.create(ContractDto.builder()
                    .id(1L)
                    .amount(BigDecimal.valueOf(10L))
                    .bankAccountDto(bankAccountDto)
                    .contractorDto(contractorDto)
                    .legalDetailDto(legalDetailDto)
                    .companyDto(companyDto)
                    .number("1")
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? contracts", e);
        }
    }

    private void initProject() {
        try {
            projectService.create(ProjectDto.builder()
                    .id(1L)
                    .name("????????????1")
                    .description("????????????????1")
                    .build());
        } catch (Exception e) {
            log.error("???? ?????????????? ?????????????????? ?????????????? projects", e);
        }
    }

}
