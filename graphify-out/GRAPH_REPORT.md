# Graph Report - JessicaLS  (2026-11-27)

## Corpus Check
- 399 files · ~327,085 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 282 file(s) not represented in the graph (top: .xml 245, .aidl 14, (none) 13)
- WARNING: 282 of 681 files seen (41%) have no supported extractor and are missing from this graph entirely. It may look complete while representing only part of the codebase.

## Summary
- 5342 nodes · 13500 edges · 261 communities (142 shown, 119 thin omitted)
- Extraction: 97% EXTRACTED · 3% INFERRED · 0% AMBIGUOUS · INFERRED: 471 edges (avg confidence: 0.85)
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- Community 0
- Community 1
- Community 2
- Community 3
- Community 4
- Community 5
- Community 6
- Community 7
- Community 8
- Community 9
- Community 10
- Community 11
- Community 12
- Community 13
- Community 14
- Community 15
- Community 16
- Community 17
- Community 18
- Community 19
- Community 20
- Community 21
- Community 22
- Community 23
- Community 24
- Community 25
- Community 26
- Community 27
- Community 28
- Community 29
- Community 30
- Community 31
- Community 32
- Community 33
- Community 34
- Community 35
- Community 36
- Community 37
- Community 38
- Community 39
- Community 40
- Community 41
- Community 42
- Community 43
- Community 44
- Community 45
- Community 46
- Community 47
- Community 48
- Community 49
- Community 50
- Community 51
- Community 52
- Community 53
- Community 54
- Community 55
- Community 56
- Community 57
- Community 58
- Community 59
- Community 60
- Community 61
- Community 62
- Community 63
- Community 64
- Community 65
- Community 66
- Community 67
- Community 68
- Community 69
- Community 70
- Community 71
- Community 72
- Community 73
- Community 74
- Community 75
- Community 76
- Community 77
- Community 78
- Community 79
- Community 80
- Community 81
- Community 82
- Community 83
- Community 84
- Community 85
- Community 86
- Community 87
- Community 88
- Community 89
- Community 90
- Community 91
- Community 92
- Community 93
- Community 94
- Community 95
- Community 96
- Community 97
- Community 98
- Community 99
- Community 100
- Community 101
- Community 102
- Community 103
- Community 104
- Community 105
- Community 107
- Community 108
- Community 109
- Community 110
- Community 111
- Community 112
- Community 113
- Community 114
- Community 115
- Community 116
- Community 117
- Community 118
- Community 119
- Community 120
- Community 121
- Community 122
- Community 123
- Community 124
- Community 125
- Community 126
- Community 127
- Community 128
- Community 129
- Community 130
- Community 131
- Community 132
- Community 133
- Community 134
- Community 135
- Community 136
- Community 137
- Community 138
- Community 139
- Community 140
- Community 141
- Community 142
- Community 143
- Community 144
- Community 145
- Community 146
- Community 147
- Community 148
- Community 149
- Community 150
- Community 151
- Community 152
- Community 153
- Community 154
- Community 155
- Community 156
- Community 157
- Community 158
- Community 159
- Community 160
- Community 161
- Community 162
- Community 163
- Community 164
- Community 165
- Community 166
- Community 167
- Community 168
- Community 169
- Community 170
- Community 171
- Community 172
- Community 173
- Community 174
- Community 175
- Community 176
- Community 177
- Community 178
- Community 179
- Community 180
- Community 181
- Community 182
- Community 183
- Community 184
- Community 185
- Community 186
- Community 188
- Community 190
- Community 191
- Community 193
- Community 194
- Community 197
- Community 198
- Community 199
- Community 200
- Community 201
- Community 204
- Community 207
- Community 208

## God Nodes (most connected - your core abstractions)
1. `IBinder` - 109 edges
2. `XposedHelpers` - 108 edges
3. `XResources` - 92 edges
4. `Context` - 73 edges
5. `Intent` - 72 edges
6. `Column` - 67 edges
7. `DaemonClient` - 64 edges
8. `logW()` - 63 edges
9. `Row()` - 63 edges
10. `ApplicationInfo` - 60 edges

## Surprising Connections (you probably didn't know these)
- `ActionDrawerHeader()` --calls--> `Row()`  [INFERRED]
  manager-ui/src/main/kotlin/org/matrix/vector/ui/ActionDrawer.kt → manager/src/main/kotlin/org/matrix/vector/manager/ui/screens/web/WebScreen.kt
- `ActionDrawerItem()` --calls--> `Row()`  [INFERRED]
  manager-ui/src/main/kotlin/org/matrix/vector/ui/ActionDrawer.kt → manager/src/main/kotlin/org/matrix/vector/manager/ui/screens/web/WebScreen.kt
- `ApiBadge()` --calls--> `Row()`  [INFERRED]
  manager-ui/src/main/kotlin/org/matrix/vector/ui/ApiBadge.kt → manager/src/main/kotlin/org/matrix/vector/manager/ui/screens/web/WebScreen.kt
- `IconCluster()` --calls--> `Row()`  [INFERRED]
  manager-ui/src/main/kotlin/org/matrix/vector/ui/IconCluster.kt → manager/src/main/kotlin/org/matrix/vector/manager/ui/screens/web/WebScreen.kt
- `ModuleRow()` --calls--> `Row()`  [INFERRED]
  manager-ui/src/main/kotlin/org/matrix/vector/ui/ModuleRow.kt → manager/src/main/kotlin/org/matrix/vector/manager/ui/screens/web/WebScreen.kt

## Import Cycles
- None detected.

## Communities (261 total, 119 thin omitted)

### Community 0 - "Community 0"
Cohesion: 0.03
Nodes (11): SplashGate(), WingedVictory(), AmbientSurface(), AppIcon(), ColorWheel(), drawThumb(), renderWheel(), toSize() (+3 more)

### Community 1 - "Community 1"
Cohesion: 0.04
Nodes (16): ConfigCache, DaemonState, ProcessScope, InstallerVerifier, PackageOptimizer, applySqliteHelperWorkaround(), ByteBufferDexClassLoader, Build (+8 more)

### Community 2 - "Community 2"
Cohesion: 0.03
Nodes (28): ServiceRegistrationWatcher, Stub, Stub, SystemServerService, getSystemServiceManager(), Stub, IUidObserver, Stub (+20 more)

### Community 3 - "Community 3"
Cohesion: 0.04
Nodes (8): ScenarioList(), CrashTraceScreen(), crashWhen(), copyToClipboard(), LogTraceScreen(), show(), labelRes(), StoreFilterSheet()

### Community 4 - "Community 4"
Cohesion: 0.04
Nodes (9): SystemProperties, XmlPullParserException, Startup, Log, Utils, CrashDumpHooker, Main, GrapheneDclHooker (+1 more)

### Community 5 - "Community 5"
Cohesion: 0.05
Nodes (21): BotBundleRow(), CommitRow(), GapRow(), HistoryFootRow(), InstalledMarkerRow(), MonthMarkerRow(), Rail(), railColor() (+13 more)

### Community 6 - "Community 6"
Cohesion: 0.06
Nodes (23): broadcastIntentCompat(), registerReceiverCompat(), startActivityAsUserCompat(), applyXspaceWorkaround(), ContentProviderHolder, IActivityManager, Stub, IApplicationThread (+15 more)

### Community 7 - "Community 7"
Cohesion: 0.04
Nodes (46): ElfImage, base_, bias_calculated_, bucket_, chain_, dynsym_, dynsym_start_, elf_debugdata_ (+38 more)

### Community 8 - "Community 8"
Cohesion: 0.06
Nodes (16): BrightnessSelector(), ColorSection(), icon(), labelRes(), SourceSwatch(), TonalPreview(), BrightnessSelector(), ColorSection() (+8 more)

### Community 9 - "Community 9"
Cohesion: 0.05
Nodes (39): IPCBridge, binder_class_, binder_ctor_, bridge_service_class_, check_service_method_, detach_fd_method_, exec_transact_backup_method_id_, exec_transact_replace_method_id_ (+31 more)

### Community 10 - "Community 10"
Cohesion: 0.04
Nodes (14): HiddenApiBridge, ActivityThread, ActivityClientRecord, ApplicationThread, LoadedApk, ApplicationInfo, AssetManager, CompatibilityInfo (+6 more)

### Community 11 - "Community 11"
Cohesion: 0.04
Nodes (24): ConfigBridge, ArraySize(), HookItem, backup, FAILED, legacy_callbacks, modern_callbacks, IsMapped() (+16 more)

### Community 12 - "Community 12"
Cohesion: 0.07
Nodes (3): XposedHelpers, InvocationTargetError, Field

### Community 13 - "Community 13"
Cohesion: 0.05
Nodes (17): Database, FakeContext, ContentResolver, Configuration, UserHandle, forWebView(), ContextWrapper, LocalizedContent() (+9 more)

### Community 14 - "Community 14"
Cohesion: 0.06
Nodes (19): fetchProcesses(), getInstalledPackagesFromAllUsers(), getInstalledPackagesReflect(), getPackageInfoCompat(), getPackageInfoCompatThrows(), getPackageInfoWithComponents(), getUserName(), isPackageAvailable() (+11 more)

### Community 15 - "Community 15"
Cohesion: 0.07
Nodes (9): ContextImpl, Context, IntentSender, CrashRecorder, LaunchShortcut, BroadcastReceiver, emit(), logW() (+1 more)

### Community 16 - "Community 16"
Cohesion: 0.05
Nodes (4): ManagerService, ManagerGuard, Stub, VersionedPackage

### Community 17 - "Community 17"
Cohesion: 0.08
Nodes (10): MethodHookParam, ParasiticManagerHooker, XC_MethodHook, XC_MethodHook, XC_MethodHook, XC_MethodHook, XC_MethodHook, XC_MethodReplacement (+2 more)

### Community 19 - "Community 19"
Cohesion: 0.07
Nodes (23): LogStatus, DaemonUnavailable, Empty, Loading, NoLogFile, NoMatches, ReadFailed, Ready (+15 more)

### Community 21 - "Community 21"
Cohesion: 0.06
Nodes (20): logE(), PendingChanges, ScopeMessage, Applied, ApplyFailed, IncludeNewAppsFailed, NothingToOpen, ToggleFailed (+12 more)

### Community 22 - "Community 22"
Cohesion: 0.06
Nodes (24): Api, api_table, impl, tbl, AppSpecializeArgs, fds_to_ignore, is_child_zygote, is_top_app (+16 more)

### Community 23 - "Community 23"
Cohesion: 0.05
Nodes (13): Context, entry_class_, inject_class_loader_, instance_, PreloadedDex, addr_, owned_, RegisterNativeMethodsInternal() (+5 more)

### Community 24 - "Community 24"
Cohesion: 0.08
Nodes (15): EmptyState(), labelRes(), ModuleFilterButton(), ModulesHeader(), ModulesScreen(), ModulesSearch(), ModuleUpdatesSheet(), SectionHeader() (+7 more)

### Community 25 - "Community 25"
Cohesion: 0.12
Nodes (10): Cli, CliRequest, ConfigCommand, DatabaseCommand, LogCommand, ModulesCommand, OutputFormatter, ScopeCommand (+2 more)

### Community 26 - "Community 26"
Cohesion: 0.08
Nodes (21): InstalledModule, XposedApi, logI(), ApiBadge(), ModuleListItem(), ModuleRow(), moduleRows(), ModuleFacts (+13 more)

### Community 28 - "Community 28"
Cohesion: 0.07
Nodes (10): SystemContext, SystemService, applyNotificationWorkaround(), VectorDaemon, DeathRecipient, ActivityManager, DdmHandleAppName, IPowerManager (+2 more)

### Community 29 - "Community 29"
Cohesion: 0.06
Nodes (20): LogToAndroid(), HookFunType, HookInline(), NativeAPIEntries, hookFunc, unhookFunc, version, NativeInit (+12 more)

### Community 30 - "Community 30"
Cohesion: 0.06
Nodes (4): PreferenceStore, InjectedModuleService, Subscriber, VectorProcessChannel

### Community 31 - "Community 31"
Cohesion: 0.09
Nodes (12): contextClickable(), onSecondaryPress(), SecondaryButton, SecondaryButtonCallback, watchSecondaryButton(), claimVerticalDrags(), document(), HtmlPane() (+4 more)

### Community 32 - "Community 32"
Cohesion: 0.10
Nodes (6): ResourceNames, XMLInstanceDetails, XC_LayoutInflated, LayoutInflatedParam, Unhook, CopyOnWriteSortedSet

### Community 33 - "Community 33"
Cohesion: 0.09
Nodes (9): ApiBadge(), StackTrace(), StackTraceElided(), StackTraceFrame(), stackTraceItems(), StackTraceSectionHeader(), PackageRow(), PanelHeader() (+1 more)

### Community 34 - "Community 34"
Cohesion: 0.08
Nodes (11): ActionToggleRow(), ModuleUpdateSection(), OrderedActions(), PackageActionResult, PackageActionSheet(), UserAvatar(), ActionDrawerHeader(), ActionDrawerItem() (+3 more)

### Community 35 - "Community 35"
Cohesion: 0.09
Nodes (4): ModuleDatabase, EnabledModuleRow, ScopeRow, CliHandler

### Community 36 - "Community 36"
Cohesion: 0.07
Nodes (6): DemoActivity, Constants, ModuleRepository, installedVersions(), RepoRepository, ServiceLocator

### Community 37 - "Community 37"
Cohesion: 0.09
Nodes (7): IntVec, LogFile, LongVec, isContinuationLine(), LogContent, LogIndex, LogScanResult

### Community 38 - "Community 38"
Cohesion: 0.11
Nodes (19): LogRow, DayBreak, Marker, buildLine(), DayBreakRow(), EntryRow(), highlighted(), levelColor() (+11 more)

### Community 39 - "Community 39"
Cohesion: 0.08
Nodes (11): ScopeScreen(), ScopeSelectMenu(), SharedSnackbarHost(), SnackbarTone, Failure, Neutral, Success, Working (+3 more)

### Community 40 - "Community 40"
Cohesion: 0.07
Nodes (16): RegisterNativeApiBridge(), VECTOR_DEF_NATIVE_METHOD(), AttributeNameSlot(), BuildDummySuperClassLoader(), FindAttributeNameMap(), GetXResourcesClassName(), IsMapped(), MapsCurrentAttributes() (+8 more)

### Community 41 - "Community 41"
Cohesion: 0.09
Nodes (3): FileSystem, LogcatMonitor, ThreadSafeLRU

### Community 42 - "Community 42"
Cohesion: 0.07
Nodes (6): VectorLogSource, LogResetKind, CLEAR, ROTATE, LogSource, WriterLabeler

### Community 43 - "Community 43"
Cohesion: 0.07
Nodes (13): get_id_vec(), main(), read_int(), recv_fd(), recv_fds(), write_int(), xrecvmsg(), DCL_HOOK_FUNC() (+5 more)

### Community 44 - "Community 44"
Cohesion: 0.08
Nodes (9): Resources, TypedArray, DisplayMetrics, XResourcesSuperClass, XTypedArraySuperClass, XModuleResources, XResForwarder, DimensionReplacement (+1 more)

### Community 45 - "Community 45"
Cohesion: 0.12
Nodes (26): logArchiveName(), ManagerPresence, buildRow(), buildSections(), CrashCard(), CrashFact(), dex2oatLabel(), FrameworkToggle() (+18 more)

### Community 46 - "Community 46"
Cohesion: 0.12
Nodes (16): Canary, CrashTrace, FrameworkUpdate, LogTrace, Route, Scope, StoreDetail, SystemStatus (+8 more)

### Community 47 - "Community 47"
Cohesion: 0.09
Nodes (12): LegacyFrameworkDelegate, LegacyPackageInfo, OriginalInvoker, VectorBootstrap, AppAttachHooker, getFieldValue(), LoadedApkCreateAppFactoryHooker, LoadedApkCreateCLHooker (+4 more)

### Community 48 - "Community 48"
Cohesion: 0.09
Nodes (6): Dex2OatServer, SelinuxObserver, Backend, BinderBackend, FallbackBackend, ShellBackend

### Community 50 - "Community 50"
Cohesion: 0.09
Nodes (15): Java_org_matrix_vector_daemon_env_LogcatMonitor_runLogcat(), Logcat, env_, modules_fd_, modules_part_, modules_written_, my_pid_, refresh_fd_method_ (+7 more)

### Community 51 - "Community 51"
Cohesion: 0.10
Nodes (4): IXUnhook, XC_MethodHook, Unhook, XposedBridge

### Community 52 - "Community 52"
Cohesion: 0.09
Nodes (9): Application, XC_MethodHook, XC_LoadPackage, LoadPackageParam, XCallback, Param, SerializeWrapper, IXposedHookLoadPackage (+1 more)

### Community 53 - "Community 53"
Cohesion: 0.14
Nodes (10): CommitPerson, GitHubRepository, Answer, Fetched, Freshness, Cached, Force, Revalidate (+2 more)

### Community 54 - "Community 54"
Cohesion: 0.09
Nodes (5): ThreadLocal<AtomicInteger>, MemberCacheKey, Method, asParcel(), ParcelUtils

### Community 56 - "Community 56"
Cohesion: 0.10
Nodes (6): LegacyApiSupport, LegacyDelegateImpl, XC_MethodHook, XC_MethodReplacement, ResourceProxy, VectorLegacyCallback

### Community 57 - "Community 57"
Cohesion: 0.10
Nodes (18): CanaryArtifact, FrameworkRelease, ZipVariant, Debug, Other, Release, BuildStamp, buildStamp() (+10 more)

### Community 58 - "Community 58"
Cohesion: 0.10
Nodes (17): currentLocale(), EmptyState(), RepoScreen(), RowBadge(), StoreFilterButton(), StoreHeader(), StoreRow(), StoreSearch() (+9 more)

### Community 59 - "Community 59"
Cohesion: 0.06
Nodes (25): AndroidLogEntry, AndroidLogEntry_t, message, messageLen, pid, priority, tag, tagLen (+17 more)

### Community 60 - "Community 60"
Cohesion: 0.11
Nodes (3): BaseService, DirectAccessService, FileResult

### Community 61 - "Community 61"
Cohesion: 0.10
Nodes (10): HttpClientFactory, NetStack, DohStatus, Bypassed, Disabled, FellBack, Untested, Working (+2 more)

### Community 62 - "Community 62"
Cohesion: 0.12
Nodes (12): GetNativeBridgeSignature(), IPCBridge::GetInstance(), ConfigImpl, obfuscation_map_t, RuntimeFlags, LATE_INJECT, VectorModule, api_ (+4 more)

### Community 63 - "Community 63"
Cohesion: 0.11
Nodes (10): DexAllocator, fd_, mapped_mem_, mapped_size_, ensureInitialized(), Java_org_matrix_vector_daemon_utils_ObfuscationManager_getSignatures(), Java_org_matrix_vector_daemon_utils_ObfuscationManager_obfuscateDex(), obfuscateDexBuffer() (+2 more)

### Community 64 - "Community 64"
Cohesion: 0.13
Nodes (3): DexTrustHooker, VectorNativeHooker, HookBridge

### Community 65 - "Community 65"
Cohesion: 0.11
Nodes (9): XC_InitPackageResources, InitPackageResourcesParam, IXposedHookCmdInit, StartupParam, IXposedHookInitPackageResources, Wrapper, IXposedHookZygoteInit, StartupParam (+1 more)

### Community 66 - "Community 66"
Cohesion: 0.12
Nodes (5): ModuleDetectionCache, Key, AppIconCache, ModuleDetection, ModuleManifest

### Community 67 - "Community 67"
Cohesion: 0.16
Nodes (14): ConfirmInstall(), RepoDetailsState, Preferences, AdditionalAuthor, Collaborator, OnlineModule, Release, ReleaseAsset (+6 more)

### Community 69 - "Community 69"
Cohesion: 0.08
Nodes (8): PackageParser, Package, PackageLite, PackageParserException, Movie, XC_MethodHook, XC_MethodHook, XC_MethodHook

### Community 70 - "Community 70"
Cohesion: 0.17
Nodes (3): NonNull, Nullable, IntRange

### Community 71 - "Community 71"
Cohesion: 0.13
Nodes (12): IntentFilter, commitForResult(), BroadcastReceiver, daemonPackageEvent(), daemonPackageEventsFlow(), BroadcastReceiver, PackageEvent, Added (+4 more)

### Community 72 - "Community 72"
Cohesion: 0.15
Nodes (8): HomeAppearanceSheet(), AppearanceSheet(), DohSettingSection(), ChoiceRow(), SheetAction(), SheetHeading(), StatusNote(), ToggleRow()

### Community 73 - "Community 73"
Cohesion: 0.09
Nodes (7): DeviceInfo, FrameworkStatus, HealthIssue, Dex2oatWrapperBroken, SepolicyNotLoaded, SystemServerNotInjected, HomeViewModel

### Community 74 - "Community 74"
Cohesion: 0.16
Nodes (3): VectorModuleManager, Generation, HotReloadingParam

### Community 76 - "Community 76"
Cohesion: 0.08
Nodes (25): Res_value, data, dataType, res0, size, TYPE_ATTRIBUTE, TYPE_DIMENSION, TYPE_DYNAMIC_ATTRIBUTE (+17 more)

### Community 77 - "Community 77"
Cohesion: 0.17
Nodes (18): Empty(), failureText(), FrameworkUpdateScreen(), InstallLog(), UpdateBar(), VersionsSheet(), formatSize(), label() (+10 more)

### Community 80 - "Community 80"
Cohesion: 0.10
Nodes (4): PackageReadyParamImplP, VectorLifecycleManager, PackageReadyParam, SystemServerStartingParam

### Community 81 - "Community 81"
Cohesion: 0.15
Nodes (4): Handler, VectorURLStreamHandler, ClassPathURLConnection, FilterInputStream

### Community 82 - "Community 82"
Cohesion: 0.10
Nodes (5): VectorFloatingNavSettings, VectorNavPanelStore, FloatingNavSettings, Ephemeral, NavPanelStore

### Community 83 - "Community 83"
Cohesion: 0.12
Nodes (14): LogFacetCounter, LogFacets, LogLevel, DEBUG, ERROR, FATAL, INFO, SILENT (+6 more)

### Community 86 - "Community 86"
Cohesion: 0.14
Nodes (16): AssetSheet(), currentLocale(), InformationTab(), InfoRow(), InstallBar(), ReadmeTab(), ReleaseBadge(), ReleaseCard() (+8 more)

### Community 88 - "Community 88"
Cohesion: 0.09
Nodes (3): VectorAmbienceSettings, AmbienceSettings, Ephemeral

### Community 89 - "Community 89"
Cohesion: 0.12
Nodes (5): ModuleLoad, Loaded, UnsupportedApi, Unusable, ObfuscationManager

### Community 90 - "Community 90"
Cohesion: 0.09
Nodes (21): CROWDIN_API_TOKEN, CROWDIN_PROJECT_ID, jobs, synchronize-with-crowdin, name, on, push, workflow_dispatch (+13 more)

### Community 91 - "Community 91"
Cohesion: 0.13
Nodes (15): ManagerCopy, Absent, Diverged, Present, canonicalDigest(), compare(), installedManager(), installedPackage() (+7 more)

### Community 94 - "Community 94"
Cohesion: 0.18
Nodes (3): FrameworkService, HotReloadTarget, ProcessKey

### Community 95 - "Community 95"
Cohesion: 0.15
Nodes (12): Abandoned, cancelByTag(), cancelModuleUpdated(), cancelScopeRequest(), createChannels(), getBitmap(), getNotificationIcon(), notActivatedTag() (+4 more)

### Community 96 - "Community 96"
Cohesion: 0.20
Nodes (13): ClosedIssue, CommunityFeed, Contributor, builtAt(), CanaryBoard, CanaryItem, Build, Installed (+5 more)

### Community 97 - "Community 97"
Cohesion: 0.20
Nodes (15): ActiveFilterRow(), LogEmptyState(), LogFilterSheet(), LogPane(), LogSearch(), LogSettingsSheet(), LogSourceToggle(), LogsScreen() (+7 more)

### Community 98 - "Community 98"
Cohesion: 0.18
Nodes (4): GitCommitCountValueSource, GitCommitHashValueSource, Parameters, GitLatestTagValueSource

### Community 99 - "Community 99"
Cohesion: 0.17
Nodes (8): FlashStep, Done, Downloading, Failed, Flashing, Idle, FrameworkInstaller, DownloadAbandoned

### Community 100 - "Community 100"
Cohesion: 0.12
Nodes (3): MainActivity, VectorTheme(), toAmoled()

### Community 101 - "Community 101"
Cohesion: 0.14
Nodes (6): Java_org_matrix_vector_daemon_env_Dex2OatServer_doMountNative(), Java_org_matrix_vector_daemon_env_Dex2OatServer_getSockPath(), Java_org_matrix_vector_daemon_env_Dex2OatServer_setSockCreateContext(), setsockcreatecon_raw(), UniqueFd, fd

### Community 102 - "Community 102"
Cohesion: 0.16
Nodes (5): ModuleInstaller, ModuleUpdateQueue, Item, State, requestReplaceExisting()

### Community 103 - "Community 103"
Cohesion: 0.13
Nodes (9): VectorStoreInstallHost, InstallStep, Confirming, Done, Downloading, Failed, Idle, Installing (+1 more)

### Community 104 - "Community 104"
Cohesion: 0.17
Nodes (4): BaseInvoker, VectorCtorInvoker, VectorMethodInvoker, Resolution

### Community 105 - "Community 105"
Cohesion: 0.24
Nodes (3): VectorHookHandle, VectorHookRegistry, IdKey

### Community 107 - "Community 107"
Cohesion: 0.11
Nodes (18): api_token_env, base_path, base_url, files, languages_mapping, source, translation, type (+10 more)

### Community 108 - "Community 108"
Cohesion: 0.19
Nodes (4): VectorStartup, HandleSystemServerProcessHooker, Callback, StartBootstrapServicesHooker

### Community 110 - "Community 110"
Cohesion: 0.13
Nodes (10): expected, var_, IOError, PAGES_MISSING, NullOrIOError, ResStringPool_ref, index, status_t (+2 more)

### Community 113 - "Community 113"
Cohesion: 0.11
Nodes (17): description, label, options, placeholder, render, value, body, attributes (+9 more)

### Community 114 - "Community 114"
Cohesion: 0.18
Nodes (8): VectorApp(), awaitPanelLongPress(), isHorizontal(), PanelBadge(), PanelBar(), PanelDrag, PanelEditDone(), PanelItem()

### Community 115 - "Community 115"
Cohesion: 0.18
Nodes (4): decodeNavPanels(), encodeNavPanels(), NavPanels, TopLevelDestination

### Community 119 - "Community 119"
Cohesion: 0.30
Nodes (4): CircuitRenderer, Pad, Pulse, Trace

### Community 120 - "Community 120"
Cohesion: 0.14
Nodes (3): RepoDetailsViewModel, StoreDataSource, StoreSettings

### Community 129 - "Community 129"
Cohesion: 0.30
Nodes (9): BuildCard(), CanaryEmpty(), CanaryScreen(), credit(), itemKey(), Preamble(), PullRequestSlot(), ReportFoot() (+1 more)

### Community 130 - "Community 130"
Cohesion: 0.31
Nodes (3): SnowRenderer, Flake, Shard

### Community 131 - "Community 131"
Cohesion: 0.21
Nodes (3): StoreCatalog, StoreLog, StoreRepository

### Community 132 - "Community 132"
Cohesion: 0.21
Nodes (4): VectorMetaDataReader, ApplicationTagVisitor, ManifestTagVisitor, MetaDataVisitor

### Community 133 - "Community 133"
Cohesion: 0.19
Nodes (7): AmbienceKind, Circuit, Matrix, Maze, None, Snow, AmbientHeader()

### Community 134 - "Community 134"
Cohesion: 0.23
Nodes (8): FeedItem, Bots, Commit, Gap, InstalledMarker, MonthMarker, FeedLayout, TimelineCommit

### Community 135 - "Community 135"
Cohesion: 0.27
Nodes (3): RootImplementation, Detection, Stub

### Community 137 - "Community 137"
Cohesion: 0.26
Nodes (3): CommitArchive, State, GhCommit

### Community 138 - "Community 138"
Cohesion: 0.14
Nodes (13): patterns, actions, maven, submodule, patterns, interval, patterns, updates (+5 more)

### Community 140 - "Community 140"
Cohesion: 0.15
Nodes (13): FrameworkState, Active, Checking, Degraded, Inactive, Mismatched, statusWordRes(), toTone() (+5 more)

### Community 141 - "Community 141"
Cohesion: 0.25
Nodes (3): Navigator, rememberNavigator(), Stable

### Community 147 - "Community 147"
Cohesion: 0.30
Nodes (3): ContributorAvatar(), drawLaurel(), Laurel()

### Community 148 - "Community 148"
Cohesion: 0.17
Nodes (12): ALIAS, COMMIT_URL, GH_TOKEN, IS_UPSTREAM, KEY_PASSWORD, KEY_STORE, KEY_STORE_PASSWORD, RUN_URL (+4 more)

### Community 150 - "Community 150"
Cohesion: 0.26
Nodes (3): VectorDeopter, TargetExecutable, VectorInlinedCallers

### Community 151 - "Community 151"
Cohesion: 0.18
Nodes (10): label, placeholder, body, attributes, type, validations, description, labels (+2 more)

### Community 152 - "Community 152"
Cohesion: 0.49
Nodes (8): GhCommitAuthor, GhCommitDetail, GhIssue, GhLicense, GhRelease, GhReleaseAsset, GhRepo, GhUser

### Community 153 - "Community 153"
Cohesion: 0.35
Nodes (6): FanItem(), fanPoints(), FloatingPanelNav(), pick(), resting(), within()

### Community 156 - "Community 156"
Cohesion: 0.20
Nodes (10): with, distribution, fetch-depth, java-version, key, name, path, restore-keys (+2 more)

### Community 158 - "Community 158"
Cohesion: 0.33
Nodes (8): endOf(), isSplitChunk(), looksLikeSplitChunk(), parseEntry(), parseLogLine(), readInt(), skipSpaces(), valueOf()

### Community 159 - "Community 159"
Cohesion: 0.42
Nodes (5): BackupRepository, BackupFile, BackupModule, BackupTarget, RestoreOutcome

### Community 160 - "Community 160"
Cohesion: 0.20
Nodes (10): event_code_t, BAD_DOCUMENT, END_DOCUMENT, END_NAMESPACE, END_TAG, FIRST_CHUNK_CODE, START_DOCUMENT, START_NAMESPACE (+2 more)

### Community 161 - "Community 161"
Cohesion: 0.20
Nodes (5): ParcelWrapper, bridge_, data, env_, reply

### Community 162 - "Community 162"
Cohesion: 0.22
Nodes (8): DemoScenario, InstallScript, FAILS_PARTWAY, NO_ROOT, SUCCEEDS, ModuleVersionScript, OUTDATED, REAL

### Community 163 - "Community 163"
Cohesion: 0.22
Nodes (3): ModulesViewModelFactory, ScopeViewModelFactory, Factory

### Community 164 - "Community 164"
Cohesion: 0.44
Nodes (7): escape(), inline(), isAlignmentRow(), linkReferences(), releaseMarkdownToHtml(), splitRow(), styleFor()

### Community 165 - "Community 165"
Cohesion: 0.22
Nodes (9): ResXMLParser, mCurExt, mCurNode, mEventCode, mTree, ResXMLTree_node, comment, header (+1 more)

### Community 166 - "Community 166"
Cohesion: 0.22
Nodes (9): ResXMLTree_attrExt, attributeCount, attributeSize, attributeStart, classIndex, idIndex, name, ns (+1 more)

### Community 169 - "Community 169"
Cohesion: 0.25
Nodes (7): if, permissions, runs-on, jobs, build, name, contents

### Community 170 - "Community 170"
Cohesion: 0.29
Nodes (3): INotificationManager, Stub, Notification

### Community 171 - "Community 171"
Cohesion: 0.25
Nodes (8): ActionId, AppInfo, ForceStop, InstallOtherUser, Open, Reoptimize, SoftReboot, Uninstall

### Community 176 - "Community 176"
Cohesion: 0.29
Nodes (7): env, CCACHE_BASEDIR, CCACHE_COMPILERCHECK, CCACHE_HARDLINK, CCACHE_NOHASHDIR, VECTOR_BUILD_COMMIT, VECTOR_BUILD_REPOSITORY

### Community 177 - "Community 177"
Cohesion: 0.29
Nodes (7): steps, id, if, name, run, shell, uses

### Community 178 - "Community 178"
Cohesion: 0.29
Nodes (7): on, merge_group, pull_request, push, workflow_dispatch, branches, tags

### Community 179 - "Community 179"
Cohesion: 0.29
Nodes (6): CommitKind, Add, Change, Chore, Fix, Remove

### Community 180 - "Community 180"
Cohesion: 0.67
Nodes (5): CrashReport, parseCrashReport(), CrashFrame, CrashSection, parseStackTrace()

### Community 182 - "Community 182"
Cohesion: 0.38
Nodes (5): LogSaveState, Failed, Idle, Saved, Saving

### Community 185 - "Community 185"
Cohesion: 0.33
Nodes (5): blank_issues_enabled, contact_links, about, name, url

### Community 186 - "Community 186"
Cohesion: 0.40
Nodes (4): ResStringPool, stringAt_, stringAtS_, StringPiece16

### Community 190 - "Community 190"
Cohesion: 0.40
Nodes (5): ResXMLTree_attribute, name, ns, rawValue, typedValue

### Community 193 - "Community 193"
Cohesion: 0.50
Nodes (4): StoreEmptiness, NoMatch, NothingPublished, Unreachable

### Community 194 - "Community 194"
Cohesion: 0.83
Nodes (3): abort_verify(), extract(), customize.sh script

### Community 197 - "Community 197"
Cohesion: 0.67
Nodes (3): BasicStringPiece, data_, length_

## Knowledge Gaps
- **328 isolated node(s):** `fd`, `fd`, `env_`, `thiz_`, `refresh_fd_method_` (+323 more)
  These have ≤1 connection - possible missing edges. (Counts symbols only; 1474 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **119 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `Context` connect `Community 15` to `Community 0`, `Community 1`, `Community 2`, `Community 3`, `Community 4`, `Community 10`, `Community 13`, `Community 14`, `Community 17`, `Community 27`, `Community 28`, `Community 159`, `Community 31`, `Community 36`, `Community 45`, `Community 49`, `Community 61`, `Community 69`, `Community 71`, `Community 91`, `Community 95`, `Community 97`, `Community 99`, `Community 102`?**
  _High betweenness centrality (0.070) - this node is a cross-community bridge._
- **What connects `fd`, `fd`, `env_` to the rest of the system?**
  _328 weakly-connected nodes found - possible missing edges._
- **Should `Community 0` be split into smaller, more focused modules?**
  _Cohesion score 0.029551820728291316 - nodes in this community are weakly interconnected._
- **Why does `ServiceLocator` connect `Community 36` to `Community 0`, `Community 3`, `Community 5`, `Community 8`, `Community 13`, `Community 15`, `Community 18`, `Community 24`, `Community 26`, `Community 27`, `Community 159`, `Community 34`, `Community 42`, `Community 46`, `Community 49`, `Community 53`, `Community 57`, `Community 61`, `Community 66`, `Community 67`, `Community 77`, `Community 82`, `Community 88`, `Community 96`, `Community 99`, `Community 100`, `Community 102`, `Community 103`, `Community 112`?**
  _High betweenness centrality (0.065) - this node is a cross-community bridge._
- **Should `Community 1` be split into smaller, more focused modules?**
  _Cohesion score 0.03920671243325705 - nodes in this community are weakly interconnected._
- **Why does `DaemonClient` connect `Community 18` to `Community 99`, `Community 36`, `Community 73`, `Community 112`, `Community 49`, `Community 21`, `Community 26`, `Community 91`, `Community 159`?**
  _High betweenness centrality (0.047) - this node is a cross-community bridge._
- **Should `Community 2` be split into smaller, more focused modules?**
  _Cohesion score 0.029188014283496353 - nodes in this community are weakly interconnected._