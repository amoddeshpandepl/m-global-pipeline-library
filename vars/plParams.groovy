def call() {
    return [
		maven: [
				maven: 'maven',
				mavenSettingsConfig:  'maven-settings'
		],
		org: 'Self',
		envs : [
			[ name : 'Sandbox', target : 'aws-mule-server', approve: false ]
		]
    ]
}
