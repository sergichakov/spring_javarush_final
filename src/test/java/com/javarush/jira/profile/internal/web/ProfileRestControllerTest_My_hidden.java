package com.javarush.jira.profile.internal.web;

/*
//@ExtendWith({SpringExtension.class,MockitoExtension.class})
//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@MockitoSettings(strictness = Strictness.LENIENT)
//@WebMvcTest(controllers = { ProfileRestController.class })
class ProfileRestControllerTest extends AbstractControllerTest {
    //https://stackoverflow.com/questions/73511395/intellij-could-not-autowire-no-beans-of-mockmvc-type-found-but-test-is-ok
    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    //@Autowired
    //private MockMvc mockMvc;
    protected org.springframework.security.core.userdetails.User loggedUser;
    @Autowired
    WebApplicationContext context;
    @Mock//Bean
    ProfileRepository profileRepository;
    @Mock
    User userLand;
    @Mock
    Profile profile;
    UsernamePasswordAuthenticationToken authenticationToken;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    //@BeforeEach //was @Before and not static
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                //.apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }
    @BeforeEach
    public void setUp1() {
        UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername("admin@aws.co")
                .password("password")
                .roles("ADMIN")
                .build();
        //when(userLand.hasRole(org.mockito.Mockito.any())).thenReturn(true);
        when(userLand.getRoles()).thenReturn(Set.of(Role.ADMIN));
        when(userLand.getEmail()).thenReturn("melchakov@Email.com");
        when(userLand.getPassword()).thenReturn("passwordMelchakov");
        //profile=new Profile();
        //profile.setId(1L);
        profile.setId(1L);
when(profile.getId()).thenReturn(1L);
        //User user=new User();
        //user.setRoles();
//when(profileRepository.getOrCreate(anyLong())).thenReturn(profile); //I really need this line
//need for update() @Test
when(profileRepository.getOrCreate(anyLong())).thenReturn(profile);//,any(ProfileTo.class)))
        when(profileRepository.save(any(Profile.class))).then(returnsFirstArg());
//        thenAnswer(new Answer<String>() {
//            @Override
//            public String answer(InvocationOnMock invocation) throws Throwable {
//                Object[] args = invocation.getArguments();
//                return (String) args[0];
//            }
//        });

        //org.springframework.security.core.userdetails.User forAuthUser=(org.springframework.security.core.userdetails.User)userDetails;
        //userDetails =new  org.springframework.security.core.userdetails.User("@testAdmin","password",userDetails.getAuthorities());
        AuthUser userDetails2 = new AuthUser(userLand);
        authenticationToken =
                new UsernamePasswordAuthenticationToken((AuthUser)userDetails2, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println("SecurityContext"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());

    }
    //@BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        loggedUser =  (org.springframework.security.core.userdetails.User)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
    @Test
    //@WithUserDetails("someUser@app.com")
    //@WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    public void shouldReturnProfileEntity_whenGoTo_endpoint_api_profile() throws Exception {
//        mockMvc.
//                perform(get("/api/profile")
//                        .withUser(user(loggedUser)))
//                .andExpect(status().isOk());
        //MvcResult s=
    mockMvc.perform(get("/api/profile")
                        //.content(mapper.writeValueAsString(new AuthUser(new User())))
                        //.contentType(MediaType.APPLICATION_JSON)
                        .principal(authenticationToken)//new UsernamePasswordAuthenticationToken(new User(), null))
                )

                .andExpect(status().isOk())//.andReturn();
                .andExpect(jsonPath("$.id").value(0));
        //System.out.println("SecurityContext"+SecurityContextHolder.getContext().getAuthentication().getPrincipal());
//        System.out.println("mockito mockMvc response="+s.getResponse().getCharacterEncoding()+" content:="+s.getResponse().getContentType()+s.getResponse().getContentAsString());
    }
    @Test
    public void shouldSaveProfileTo_to_ProfileRepository() throws Exception {
        String url =  "/api/profile";
        ContactTo contactTo= new ContactTo("CodeConstructor","valueConstructor");
        ProfileTo anObject = new ProfileTo(1L,Set.of("mailNotifications"),Set.of(contactTo));
        anObject.setId(1L);

        //... more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject );

        MvcResult profile1=mockMvc.perform(post(url).contentType(APPLICATION_JSON_UTF8)
                        .principal(authenticationToken)
                        .content(requestJson))
                .andReturn();
        System.out.println("MVCRusult proxy"+profile1.getResponse().getContentAsString());
//                .andExpect(status().isOk());
        //mockMvc.perform(put("/api/profile").principal(authenticationToken))
    }
    //@Test
    public void test() throws Exception {
        mockMvc.perform(get("/api/profile")
                    .principal(new UsernamePasswordAuthenticationToken(new User(), null))
                ).andExpect(status().isOk());
    }
    //@Test
    @WithMockUser(username = "user1", password = "pwd", roles = "ADMIN")
    void shouldReturnProfileEntity_whenGetOrCreate_long_id()throws Exception {
        Profile profile=new Profile();
        profile.setId(1L);
        ObjectMapper mapper = new ObjectMapper();
        //User user=new User();
        //user.setRoles();
        when(profileRepository.getOrCreate(anyLong())).thenReturn(profile);
        mockMvc.perform(get("/api/profile")
                        //.content(mapper.writeValueAsString(new AuthUser(new User())))
                        //.contentType(MediaType.APPLICATION_JSON)
                        .principal(new UsernamePasswordAuthenticationToken(new User(), null))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));


        Authentication authentication = Mockito.mock(Authentication.class);
        User userDetails = Mockito.mock(User.class);

        AuthUser authUser=new AuthUser(userDetails);
        Mockito.when(authentication.getPrincipal()).thenReturn(authUser);

    }
}

 */