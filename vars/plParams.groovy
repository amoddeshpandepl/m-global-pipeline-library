def call() {
    return [
		maven: [
				mavenSettingsConfig:  'maven-settings',
				ciCmdExtraFlags: '-DskipTests'
		],
		org: 'Self',
		envs : [
			[ name : 'Sandbox', target : 'aws-mule-server', approve: false ]
		]
    ]
}
